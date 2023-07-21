import java.net.*;
public class TCPPortScanner {
 public static void main(String args[]) {
        for(int port=1; port<=65535; port++) {
          try {
              Socket socket = new Socket("127.0.0.1",port);
              System.out.println("Port in use: " + port );
              socket.close();
            }
          catch (Exception e) {
              System.out.println("Port not in use: " + port );
			}
        }
    }
}