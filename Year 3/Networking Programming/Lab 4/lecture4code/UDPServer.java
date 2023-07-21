import java.net.*;
public class UDPServer {
	public static void main(String[] args) {
		try {
			DatagramSocket server = new DatagramSocket(5000);
			byte data[] = new byte[ 100 ];
			DatagramPacket receivePacket = new DatagramPacket( data, data.length );
			System.out.println("Server is Running");
			server.receive(receivePacket);
			System.out.println(new String (receivePacket.getData()));
			server.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}