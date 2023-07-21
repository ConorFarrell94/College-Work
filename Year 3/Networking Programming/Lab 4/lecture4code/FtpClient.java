import java.net.*;
import java.io.*;
import org.apache.commons.net.ftp.FTPClient;
class FtpClient{
    public static void main(String[] args) {
		FTPClient ftpClient = new FTPClient();
	    try {
			ftpClient.connect("ftp.ietf.org");// Connect to the localhost
			if (ftpClient.login("anonymous", "")) // login to ftp server
                System.out.println("FTP Successfully logged in!");
            else 
                return;
            String fileName = "rfc0978.txt";
            FileOutputStream fos = new FileOutputStream(fileName);
			System.out.println("FTP Changing Working Directory");
			ftpClient.changeWorkingDirectory("/rfc");
			if (ftpClient.retrieveFile(fileName, fos))// Download file from the ftp server
				System.out.println("File downloaded successfully !");
            else 
                System.out.println("File downloading failed !");
            ftpClient.logout();
        } 
		catch (Exception e) {
            System.out.println(e);
		}      
	}
}