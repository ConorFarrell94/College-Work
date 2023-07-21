import java.net.*; 
import java.io.*;
import java.net.InetAddress;
import org.apache.commons.net.daytime.DaytimeUDPClient;
class MyUDPClient{
    public static void main(String[] args) {
		try {
		 DaytimeUDPClient client = new DaytimeUDPClient();
		 InetAddress address = InetAddress.getByName("time.nist.gov");
		 client.setDefaultTimeout(6000);
		 client.open();
		 System.out.println("Getting the Time from UDP Server");
		 System.out.println(client.getTime(address,13));
		
		}	
		catch (Exception e) {
            System.out.println(e);
		}
	}
}