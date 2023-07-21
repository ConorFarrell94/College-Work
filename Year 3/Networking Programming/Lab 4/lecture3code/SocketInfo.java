import java.net.*;
import java.io.*;
public class SocketInfo {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("www.dit.ie", 80);
			System.out.println("Connected to " + socket.getInetAddress() + " on port " + socket.getPort());
			System.out.println("From " + socket.getLocalAddress() + " on port " + socket.getLocalPort());
		} 
		catch (Exception ex) {
			System.err.println(ex);
		} 
	}
}