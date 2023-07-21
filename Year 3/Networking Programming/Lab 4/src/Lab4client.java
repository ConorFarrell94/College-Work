import java.net.*;

public class Lab4client {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            String message = "Hello Server";
            byte data[] = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
            System.out.println("Sending Data to Server");
            byte[] buffer = new byte[512];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            DatagramSocket socket = new DatagramSocket();
            socket.receive(response);
            String quote = new String(buffer, 0, response.getLength());
            System.out.println(quote);
            System.out.println();
            client.send(sendPacket);
            client.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
