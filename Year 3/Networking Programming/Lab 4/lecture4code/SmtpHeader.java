import java.net.*;
import java.io.*;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;
class SmtpHeader{
    public static void main(String[] args) {
		String sender = "aneel.rahim@dit.ie";
		String recipient = "aneel.rahim@dit.ie";
		String subject = "SMTP Testing";
		try {
			SMTPClient client = new SMTPClient( );
			client.connect("127.0.0.1");
			client.setSender(sender);
            client.addRecipient(recipient);
			Writer writer = client.sendMessageData();
			SimpleSMTPHeader header = new SimpleSMTPHeader(sender, recipient, subject);
			writer.write(header.toString());
			writer.write("Hi! I am testing SMTP Header");
			writer.close();
			client.completePendingCommand();
			client.disconnect( );
		}	
		catch (Exception e) {
            System.out.println(e);
		}
	}
}