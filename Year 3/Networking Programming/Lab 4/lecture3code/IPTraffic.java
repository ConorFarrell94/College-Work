import java.net.*;
import java.io.*;
public class IPTraffic {
	public static void main(String[] args) {
		int IPTOS_LOWCOST = 2;
		int IPTOS_RELIABILTY = 4;
        int IPTOS_THROUGHPUT = 8;
        int IPTOS_LOWDELAY = 16; 
		try {
			Socket s1 = new Socket("www.yahoo.com", 80);
			s1.setTrafficClass(IPTOS_LOWCOST); 
			Socket s2 = new Socket("www.yahoo.com", 80);
			s2.setTrafficClass(IPTOS_RELIABILTY); 
			Socket s3 = new Socket("www.yahoo.com", 80);
			s3.setTrafficClass(IPTOS_THROUGHPUT|IPTOS_LOWDELAY); 
			System.out.println(s3.getTrafficClass());
		}
		catch (Exception ex) {
			System.err.println(ex);
		}	
	}
}