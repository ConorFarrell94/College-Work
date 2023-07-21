import java.io.*;
import java.net.*;
public class UDPClient {
	 public static void main(String[] args) {
		 int PORT = 13;
		 String HOSTNAME = "time.nist.gov";
		 try  {
		     DatagramSocket socket = new DatagramSocket();
			 socket.setSoTimeout(10000);
			 InetAddress host = InetAddress.getByName(HOSTNAME);
			 DatagramPacket request = new DatagramPacket(new byte[1], 1, host , PORT);
			 DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
			 socket.send(request);
			 socket.receive(response);
			 String result = new String(response.getData(), 0, response.getLength(),"US-ASCII");
			 System.out.println(result);
			} 
		 catch (Exception e) {
			 System.out.println(e);
			}
		} 
	}