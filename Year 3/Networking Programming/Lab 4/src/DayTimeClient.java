import java.net.*;
import java.io.*;
public class DayTimeClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 4000);
			socket.setSoTimeout(15000);
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			System.out.println(in.readObject());
			socket.close();
		} 
		catch (Exception ex) {
			System.err.println(ex); 
		}
	}
}