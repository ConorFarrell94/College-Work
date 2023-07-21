import java.net.*;
import java.io.*;
import java.util.Scanner; 
public class EchoClient {
	public static void main(String[] args) {
		try {
			Socket client = new Socket( "localhost",5000 ); 
			System.out.println("Client is connected to Server");
			ObjectOutputStream out = new ObjectOutputStream( client.getOutputStream() );
			ObjectInputStream in = new ObjectInputStream( client.getInputStream() );
			Scanner input = new Scanner( System.in);
			while (true) {
				System.out.println("Enter your Message");
				String message = input.nextLine();
				out.writeObject(message);
				message = ( String ) in.readObject();
				System.out.println ("Data from Server:  " + message);
			}
		} 
		catch (Exception ex) {
			System.err.println(ex);
		} 
	}
}