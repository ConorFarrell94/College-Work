import java.net.*;
import java.io.*;
import java.util.*;
public class MultiUDPServer {
     public static void main(String[] args) {
         DatagramSocket server = null;
         try {
              server = new DatagramSocket(5001);
              System.out.println("Server is Running on port 5001");
            }
 		 catch (Exception ex) {
             System.err.println(ex);
			}
         try {
             Thread r = new ReadingThread(server);
			 Thread w = new WritingThread(server);
             r.start();
			 w.start();
			}
		 catch (Exception ex) {
                System.err.println(ex);
	}	}	} 
 
class ReadingThread extends Thread {
     private DatagramSocket readingserver;
     public static int clientport;
	 public ReadingThread (DatagramSocket server){
          readingserver = server;
	    }
     public void run() {
         byte data[] = new byte[ 100 ];
		 DatagramPacket receivePacket = new DatagramPacket( data, data.length );
		 try {
			 readingserver.receive(receivePacket);
		     clientport = receivePacket.getPort();
			 System.out.println(new String (receivePacket.getData()));
			 while (true){
			     readingserver.receive(receivePacket);
			     System.out.println(new String (receivePacket.getData()));  
			    }  
			}
         catch (Exception ex) {
             System.err.println(ex);
	}   }   } 

class WritingThread extends Thread {
     
	 private DatagramSocket writingserver;
     public WritingThread (DatagramSocket server){
         writingserver= server;
	    }
     public void run() {
         try {
             String message = null;
             Scanner s = new Scanner(System.in);
			 byte data [] ;   
			 while (true){
	              message = s.nextLine();
                 data = message.getBytes();
			     DatagramPacket sendPacket = new DatagramPacket( data, data.length,
				     InetAddress.getLocalHost(),ReadingThread.clientport);
			     System.out.println("Sending Data to Client");
			     writingserver.send(sendPacket);			
			    }
			}
         catch (Exception ex) {
             System.err.println(ex);
	}   }   } 