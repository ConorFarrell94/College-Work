import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class SSLSessionClient {
	public static void main(String[] args) {
		 System.setProperty("javax.net.ssl.trustStore", "client-trustStore.jks");
		 try {
		     SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		     Socket client = factory.createSocket("localhost", 5432);
		     SSLSession session = ((SSLSocket) client).getSession();
			 System.out.println("Peer host is " + session.getPeerHost());
			 System.out.println("Cipher is " + session.getCipherSuite());
			 System.out.println("Protocol is " + session.getProtocol());
			 System.out.println("ID is " + session.getId());
			 System.out.println("Session created in " + session.getCreationTime());
			 System.out.println("Session accessed in " + session.getLastAccessedTime());
			 ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			 String message = ( String ) in.readObject();
			 System.out.println ("Data from Server: " + message);
			 client.close();
			} 
		 catch (Exception ex) {
			 System.err.println(ex);
	}   }   }