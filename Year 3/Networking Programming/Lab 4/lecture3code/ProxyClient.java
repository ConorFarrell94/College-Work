import java.net.*; 
import java.io.*;
public class ProxyClient {
	public static void main(String[] args) {
		try {
			SocketAddress proxyAddress = new InetSocketAddress("119.23.204.52",1080); // setup proxy
			Proxy proxy = new Proxy(Proxy.Type.SOCKS, proxyAddress);
			Socket s = new Socket(proxy); // setup socket
			SocketAddress remote = new InetSocketAddress("www.google.ie", 8080);
			s.connect(remote); //connect
			System.out.println("Successfully connected to Server using Proxy Server");
		}
		catch (Exception ex) {
				System.err.println(ex);
		}	
	}
}