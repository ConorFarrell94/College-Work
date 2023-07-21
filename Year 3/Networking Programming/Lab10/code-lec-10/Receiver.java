import java.net.*;
import java.io.*;
public class Receiver {
     public static void main( String[] args ){
	     try {
             MulticastSocket ms = new MulticastSocket(4000);
			 InetAddress group = InetAddress.getByName("224.2.2.2");
			 System.out.println("Joining the MulticastGroup"); 
			 ms.joinGroup(group);
			 byte[] buffer = new byte[100];
			 DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			 System.out.println("Waiting for Server Message"); 
			 ms.receive(dp);
			 System.out.println("Getting Message from Server " + new String (dp.getData()));
			 System.out.println("Leaving the MulticastGroup"); 
			 ms.leaveGroup(group);
			 ms.close();
            }
		 catch (Exception ex) {
			 System.err.println(ex);
    }   }   }