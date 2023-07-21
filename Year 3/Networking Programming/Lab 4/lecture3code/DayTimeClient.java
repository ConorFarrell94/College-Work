import java.net.*;
import java.io.*;

public class DayTimeClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("time.nist.gov", 13);
			socket.setSoTimeout(15000);
			InputStream in = socket.getInputStream();
			int c;
			while ((c = in.read())!= -1) 
				System.out.print((char)c);
			socket.close();
			} 
		catch (IOException ex) {
				System.err.println(ex);} 
	}
}

