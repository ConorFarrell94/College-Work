import java.net.*;
import java.io.*;
import org.apache.commons.net.smtp.SMTPClient;
class SmtpClient{
    public static void main(String[] args) {
		try {
			SMTPClient client = new SMTPClient( );
			client.connect("127.0.0.1");
			client.sendSimpleMessage("aneel.rahim@dit.ie", 
			"aneel.rahim@dit.ie", 
			"Hi! I am testing SMTP working." );
			client.disconnect( );
		}	
		catch (Exception e) {
            System.out.println(e);
		}
	}
}