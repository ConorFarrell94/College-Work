import java.net.*;
import java.io.*;
import java.util.*;
public class MultiUDPClient {
     public static void main(String[] args) {
         DatagramSocket client = null;
         try {
              client = new DatagramSocket();
            }
 		 catch (Exception ex) {
             System.err.println(ex);
			}
         try {
             Thread r = new ReadingThread(client);
			 Thread w = new WritingThread(client);
             w.start();
		     r.start();
			}
		 catch (Exception ex) {
                System.err.println(ex);
	}	}	} 
 
class ReadingThread extends Thread {
     private DatagramSocket readingserver;
     public ReadingThread (DatagramSocket server){
          readingserver = server;
	    }
     public void run() {
         byte data[] = new byte[ 100 ];
		 DatagramPacket receivePacket = new DatagramPacket( data, data.length );
		 try {
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
             String message;
             Scanner s = new Scanner(System.in);
			 byte data [] ;   
			 while (true){
			     message = s.nextLine();
                 data = message.getBytes();
			     DatagramPacket sendPacket = new DatagramPacket( data, data.length,
				      InetAddress.getLocalHost(),5001);
			     System.out.println("Sending Data to Server");
			     writingserver.send(sendPacket);			
			}
         catch (Exception ex) {
             System.err.println(ex);
	}   }   } 