import java.net.*;
import java.io.*;
public class Server {
     public static void main(String[] args) {
         ServerSocket server = null;
          try {
              server = new ServerSocket( 5000, 10 ); // create ServerSocket
              System.out.println("Server is Running on port 5000");
            }
 		  catch (Exception ex) {
             System.err.println(ex);
			}

         while (true){
             try {
                 System.out.println("Waiting for Client to Connect");
                 Socket client = server.accept();
                 ConnectionThread t = new ConnectionThread(client);
                 t.start();
                }
			 catch (Exception ex) {
                 System.err.println(ex);
	}	}	}	} 
 
class ConnectionThread extends Thread {
     private Socket connection;
     public ConnectionThread (Socket con){
     connection = con;
	}
     public void run() {
         try {
             ObjectOutputStream out = new ObjectOutputStream( connection.getOutputStream() );
             ObjectInputStream in = new ObjectInputStream( connection.getInputStream() );
             String message = "Connection successful";
             out.writeObject(message);
             message = ( String ) in.readObject();
             System.out.println ("Data from Client: " + message);
             connection.close();
            }
         catch (Exception ex) {
             System.err.println(ex);
	}   }   } 