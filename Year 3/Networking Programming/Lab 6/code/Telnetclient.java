import java.io.*;
import org.apache.commons.net.telnet.TelnetClient;
public class Telnetclient {
	 public static void main(String[] args){
         TelnetClient telnet = new TelnetClient();
		 try {
             telnet.connect("telehack.com",23);
			 InputStream input = telnet.getInputStream();
			 int c;
			 while ((c = input.read())!= -1) 
				System.out.print((char)c);
			}
         catch (Exception e){
             System.out.println(e);
			}
		}
	}