import java.net.*;
import java.io.*;
public class Whois {
	public static void main(String[] args) {
		try {
			int c;
			// Create a socket connected to internic.net, port 43.
			Socket s = new Socket("whois.internic.net", 43);
			// Obtain input and output streams.
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			String str = "google.com"+ "\n";
			out.write(str.getBytes());
			while ((c = in.read())!= -1) 
			System.out.print((char)c);
			s.close();
		} 
		catch (IOException ex) {
			System.err.println(ex);
		} 
	}
}

