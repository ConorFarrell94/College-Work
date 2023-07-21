import java.net.*;
import java.io.*;
public class Client {
	public static void main(String[] args) {

		try {
			Socket client = new Socket( "localhost",5000 ); 
			System.out.println("Client is connected to Server");
			ObjectOutputStream out = new ObjectOutputStream( client.getOutputStream() );
			ObjectInputStream in = new ObjectInputStream( client.getInputStream() );
			String message = ( String ) in.readObject();
			System.out.println ("Data from Server:  " + message);
			message = "Hello";
			out.writeObject(message);
			client.close();
		} 
		catch (Exception ex) {
			System.err.println(ex);
		}
	}
}