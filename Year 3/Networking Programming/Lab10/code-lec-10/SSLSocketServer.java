import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class SSLSocketServer {
	public static void main(String[] args) {
		try {
		     SSLServerSocketFactory factory = (SSLServerSocketFactory)
			     SSLServerSocketFactory.getDefault();
		     ServerSocket server = factory.createServerSocket(5432);
		     while (true) {
			     Socket client = server.accept();
			     ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			     out.writeObject("Hi");
			     out.close();
			     client.close();
			}   } 
		catch (Exception ex) {
			 System.err.println(ex);
	}   }   }