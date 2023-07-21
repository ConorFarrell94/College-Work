import java.net.*;
import java.io.*;
import javax.net.ssl.SSLSocketFactory;
public class SSLSimpleClient {
	public static void main(String[] args) {
		 try {
			 SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			 Socket socket = factory.createSocket("www.usps.com",443);
			 System.out.println("SSL Socket created with www.usps.com port 443");
			 Writer out = new OutputStreamWriter(socket.getOutputStream(),"US-ASCII");
			 System.out.println("Sending Credit Card Details...");
			 out.write("Name: Aneel Rahim\r\n");	
			 out.write("Product-ID: 67X-89\r\n");
			 out.write("Address: DIT kevin Street Dublin Ireland\r\n");
			 out.write("Card number: 4000-1234-5678-9017\r\n");
			 out.write("Expires: 08/05\r\n");
			 out.flush();
		    } 
		 catch (Exception ex) {
			 System.err.println(ex);
    }   }   }