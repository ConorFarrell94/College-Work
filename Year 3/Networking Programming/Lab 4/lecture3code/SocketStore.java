import java.net.*;
import java.io.*;
public class SocketStore {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("www.yahoo.com", 80);
			System.out.println("Successfully connected to yahoo");
			System.out.println("Saving the Socket Address");
			SocketAddress yahoo = socket.getRemoteSocketAddress();
			System.out.println("Closing the Socket");
			socket.close();
			//Later, you could reconnect to Yahoo! using this address:
			Socket socket2 = new Socket();
			socket2.connect(yahoo);
			System.out.println("Again connected to yahoo using socket Address");
		}
		catch (IOException ex) {
		System.err.println(ex);
		}	
	}
}