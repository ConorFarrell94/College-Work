import java.io.*;
import java.net.*;
public class DictClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("dict.org", 2628);
			socket.setSoTimeout(15000);
			OutputStream out = socket.getOutputStream();
			Writer writer = new OutputStreamWriter(out, "UTF-8");     
			InputStream in = socket.getInputStream();
			Reader reader  = new InputStreamReader(in, "UTF-8");     
			writer.write("define fd-eng-gle " + args [0] + "\r\n");
			writer.flush();    
			int c;
			while((c= reader.read()) != -1)
			System.out.print((char)c);
			writer.write("quit\r\n");
			writer.flush();
			socket.close();
		} 
		catch (Exception ex) {
			System.err.println(ex);
		} 
    } 
}