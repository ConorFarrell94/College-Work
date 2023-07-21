import java.net.*;
import java.io.*;
import java.util.*;
public class client {
    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket( "localhost",5000 );
            System.out.println("Client is connected to Server");
        }
        catch (Exception ex) {
            System.err.println(ex);
        }

        try {
            Thread r = new clientRead(client);
            Thread w = new clientWrite(client);
            w.start();
            r.start();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}

class clientRead extends Thread {
    private Socket connection;
    public clientRead(Socket con){
        connection = con;
    }
    public void run() {
        try {

            ObjectInputStream in = new ObjectInputStream( connection.getInputStream() );
            String message;
            while (true){
                message = ( String ) in.readObject();
                System.out.println ("Client reads : " + message);
            }

        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}

class clientWrite extends Thread {
    private Socket connection;
    public clientWrite(Socket con){
        connection = con;
    }
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream( connection.getOutputStream() );
            String message;
            Scanner s = new Scanner(System.in);
            while (true){
                System.out.print("Client output : ");
                message = s.nextLine();
                out.writeObject(message);
            }   }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
