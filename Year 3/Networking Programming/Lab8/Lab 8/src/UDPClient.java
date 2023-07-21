import javax.swing.*;
import java.awt.*;
import java.net.*;

public class UDPClient extends JFrame{
	public static void main(String[] args) {
		UDPClient application = new UDPClient();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public UDPClient() {
		JLabel label = new JLabel("Server Data will print here");
		Container container = getContentPane();
		container.setLayout( new FlowLayout() );
		container.add(label);
		setSize( 400, 300 );
		setVisible( true );
		try {
			DatagramSocket client = new DatagramSocket(5000);
			byte data[] = new byte[ 100 ];
			DatagramPacket receivePacket = new DatagramPacket( data, data.length );
			System.out.println("Client is Running");
			client.receive(receivePacket);
			System.out.println(new String (receivePacket.getData()));
			byte[] text = receivePacket.getData();
			label.setText(String.valueOf(text));
			System.out.println("Data Received");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	}