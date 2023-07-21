import java.net.*;
import java.io.*;
public class SocketAdd {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket();
			// fill in socket options
			SocketAddress address = new InetSocketAddress("time.nist.gov", 13);
			socket.connect(address);
			// work with the sockets...
			InputStream in = socket.getInputStream();
			int c;
			while ((c = in.read())!= -1) 
				System.out.print((char)c);
			socket.close();
		}
		catch (IOException ex) {
		System.err.println(ex);
		}	
	}
}