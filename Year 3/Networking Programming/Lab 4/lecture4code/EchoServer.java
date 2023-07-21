import java.net.*;
import java.io.*;
public class EchoServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket( 5000, 10 ); // create ServerSocket
			System.out.println("Server is Running on port 5000");
			Socket connection = server.accept();
			ObjectOutputStream out = new ObjectOutputStream( connection.getOutputStream() );
			ObjectInputStream in = new ObjectInputStream( connection.getInputStream() );
			String message = "Connection successful";
			while (true) {
				message = ( String ) in.readObject();
				System.out.println ("Data from Client: " + message);
				out.writeObject(message);
			}
		} 
		catch (Exception ex) {
			System.err.println(ex);
		} 
	}
}