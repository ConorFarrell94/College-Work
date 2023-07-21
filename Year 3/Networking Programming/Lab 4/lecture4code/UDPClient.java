import java.net.*;
public class UDPClient {
	public static void main(String[] args) {
		try {
			DatagramSocket client = new DatagramSocket();
			String message = "Hello Server";
			byte data [] =  message.getBytes();
			DatagramPacket sendPacket = new DatagramPacket( data, data.length,InetAddress.getLocalHost(),5000);
			System.out.println("Sending Data to Server");
			client.send(sendPacket);
			client.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
}