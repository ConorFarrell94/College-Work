import java.net.*; 
import java.io.*;
import org.apache.commons.net.daytime.DaytimeTCPClient;
class MyTCPClient{
    public static void main(String[] args) {
		try {
		DaytimeTCPClient client = new DaytimeTCPClient();
		client.connect("time.nist.gov");
		System.out.println("Connected Successfully to NIST Server");
		System.out.println("Getting the Time from Server");
		System.out.println(client.getTime());
		client.disconnect( );
		}	
		catch (Exception e) {
            System.out.println(e);
		}
	}
}