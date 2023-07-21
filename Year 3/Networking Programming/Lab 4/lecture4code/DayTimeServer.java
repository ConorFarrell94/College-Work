import java.net.*;
import java.io.*;
import java.util.Date;
public class DayTimeServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(4000);
			Socket connection = server.accept();
			ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
			Date now = new Date();
			out.writeObject(now);
			connection.close();
		} 
		catch (IOException ex) {
			System.err.println(ex);
		}
	}
}