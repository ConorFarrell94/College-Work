import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
public class Lab4server {

public static void main(String[] args) {
            int port = 8080;
            if (args.length == 1) {
                port = Integer.valueOf(args[0]);
            }
            DatagramSocket aSocket = null;

            try {
                System.out.println("Starting UDP TIME SERVER - listening on port: " + port);
                aSocket = new DatagramSocket(port);
                byte[] receiveData = new byte[1000];
                byte[] sendData = new byte[1000];
                while (true) {
                    Date date = new Date();
                    DatagramPacket recievePacket = new DatagramPacket(receiveData, receiveData.length);
                    aSocket.receive(recievePacket);
                    InetAddress IPAddress = recievePacket.getAddress();
                    int clientPort = recievePacket.getPort();
                    sendData = date.toString().getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IPAddress,clientPort);
                    aSocket.send(sendPacket);
                }
            } catch (SocketException e) {
                System.out.println(
                        "Socket: " + e.getMessage()
                );
            } catch (IOException e) {
                System.out.println(
                        "IO: " + e.getMessage()
                );
            } finally {
                if (aSocket != null) {
                    aSocket.close();
                }
            }
        }

    }

