import java.net.*;
import java.io.*;
import javax.net.ssl.SSLSocketFactory;
public class SSLSimpleClient2 {
	public static void main(String[] args) {
		try {
			 SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
             Socket socket = factory.createSocket("www.usps.com", 443);// default https port
             Writer out = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
             out.write("GET http://www.usps.com/ HTTP/1.1 \r\n");
			 out.write("Host:www.usps.com\r\n");
			 out.write("\r\n");
			 out.flush();
             out.flush();
			 BufferedReader in = new BufferedReader(
			 new InputStreamReader(socket.getInputStream())); 
			 String s;
			 while (!(s = in.readLine()).equals("")) {
			     System.out.println(s);
			}   } 
		 catch (Exception ex) {
			 System.err.println(ex);
	}   }   }