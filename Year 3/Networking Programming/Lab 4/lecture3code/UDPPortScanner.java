import java.net.*;
import java.io.*;
import java.sql.Time;

public class UDPPortScanner {
	public static void main(String[] args) {
		for (int port = 1; port <= 5000; port++) {
			try {
				// the next line will fail and drop into the catch block if
				// there is already a server running on port i
				DatagramSocket server = new DatagramSocket(port);
				server.close();
			} 
			catch (SocketException ex) {
				System.out.println("There is a server on port " + port + ".");
			}
		}
	}
}