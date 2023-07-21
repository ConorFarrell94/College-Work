import java.net.*;
import java.io.*;
import java.util.*;
public class server {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket( 5000, 10 ); // create ServerSocket
            System.out.println("Server is Running on port 5000");
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            System.out.println("Waiting for Client to Connect");
            Socket client = server.accept();
            Thread r = new serverRead(client);
            Thread w = new serverWrite(client);
            w.start();
            r.start();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}

class serverRead extends Thread {
    private Socket connection;
    public serverRead(Socket con){
        connection = con;
    }
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream( connection.getInputStream() );
            String message;
            while (true){
                message = ( String ) in.readObject();
                System.out.println ("Server reads : " + message);
            }

        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}

class serverWrite extends Thread {

    private Socket connection;
    public serverWrite(Socket con){
        connection = con;
    }
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream( connection.getOutputStream() );
            String message;
            Scanner s = new Scanner(System.in);
            while (true){
                System.out.print("Server output : ");
                message = s.nextLine();
                out.writeObject(message);
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
