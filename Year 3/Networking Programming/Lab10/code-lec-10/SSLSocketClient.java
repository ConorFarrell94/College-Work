import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class SSLSocketClient {
	public static void main(String[] args) {
		 try {
		     SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		     Socket client = factory.createSocket("localhost", 5432);
		     ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			 String message = ( String ) in.readObject();
			 System.out.println ("Data from Server: " + message);
			 client.close();
		    } 
		 catch (Exception ex) {
			 System.err.println(ex);
	}   }   }