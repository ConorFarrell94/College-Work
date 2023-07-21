import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class UDPServer extends JFrame implements ActionListener {
    JButton b1;
    JTextField t1;
    Container container = getContentPane();

    public static void main(String[] args) {
		UDPServer application = new UDPServer();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
    }

	public UDPServer() {
		container.setLayout(new FlowLayout());
		b1 = new JButton("Send to Client");
		t1 = new JTextField(30);
		container.add(b1);
		container.add(t1);
		setSize(400, 300);
		setVisible(true);
		b1.addActionListener(this);
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DatagramSocket server = new DatagramSocket(5000);
            String message = t1.getText();
            byte[] data = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
            System.out.println("Sending Data to Client");
            server.send(sendPacket);
			System.out.println("Data Sent");
//			server.close();
        } catch (IOException socketException) {
            socketException.printStackTrace();
        }
	}
}