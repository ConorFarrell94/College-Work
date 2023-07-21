import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Sender {
	 public static void main( String[] args ){
		 try {
		     InetAddress ia = InetAddress.getByName("224.2.2.2");
			 int port = 4000;
			 MulticastSocket ms = new MulticastSocket(5000);
			 Scanner input = new Scanner( System.in );
			 System.out.println("Enter message for Clients "); 
			 String message = input.nextLine(); 
			 byte[] data= message.getBytes();
			 DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
			 System.out.println("Sending message for Clients ");
			 ms.send(dp);
			}
		 catch (Exception ex) {
			 System.err.println(ex);
	}   }   }