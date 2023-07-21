import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class SSLSessionServer {
	public static void main(String[] args) {
		 System.setProperty("javax.net.ssl.keyStore", "server-keystore.jks");
         System.setProperty("javax.net.ssl.keyStorePassword", "server");
		 try {
			 SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			 ServerSocket server = factory.createServerSocket(5432);
			 Socket client = server.accept();
			 SSLSession session = ((SSLSocket) client).getSession();
			 System.out.println("Peer host is " + session.getPeerHost());
			 System.out.println("Cipher is " + session.getCipherSuite());
			 System.out.println("Protocol is " + session.getProtocol());
			 System.out.println("ID is " + session.getId());
			 System.out.println("Session created in " + session.getCreationTime());
			 System.out.println("Session accessed in " + session.getLastAccessedTime());
			 ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			 out.writeObject("Hi");
			 out.close();
			 client.close();
			}
		 catch (Exception ex) {
			 System.err.println(ex);
		}
	}
}