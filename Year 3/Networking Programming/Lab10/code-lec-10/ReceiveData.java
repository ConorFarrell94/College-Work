import java.net.*;
import java.io.*;
public class ReceiveData {
     public static void main(String[] argv) throws Exception {
         try {
			 String groupName = "224.2.2.2";
			 int port = 4000;
			 MulticastSocket msocket = new MulticastSocket(port);
			 System.out.println("Creating MulticastSocket at 4000");
			 InetAddress group = InetAddress.getByName(groupName);
			 System.out.println("Joining MulticastGroup at 224.2.2.2");
			 msocket.joinGroup(group);
			 byte[] data = new byte[1024];
			 DatagramPacket dp = new DatagramPacket(data, data.length, group, port);
			 System.out.println("Waiting for Data from MulticastGroup....");
			 msocket.receive(dp);
			 System.out.println("Received Data from MulticastGroup");
            }
		 catch (Exception ex) {
			 System.err.println(ex);
    }   }   }