import java.net.*;
import java.util.Scanner;

public class Lab3 {

    public static void main(String[]args)throws Exception {
        String IP = "2.0.0.127";// ipis 127.0.0.2 we reverse it
        String website = ".sbl.spamhaus.org";//www.spamhaus.org/sbl/
        String query;
        try {
            query = IP + website;
            InetAddress.getByName(query);
            System.out.println(IP + " is a known spammer.");
        } catch (Exception e) {
            System.out.println(IP + " appears legitimate");
        }

        System.out.println("Enter IP to check: ");
        Scanner input = new Scanner(System.in);
        String IP2 = input.nextLine();

        String website2 = ".sbl.spamhaus.org";//www.spamhaus.org/sbl/
        String query2;
        try {
            query2 = IP2 + website2;
            InetAddress.getByName(query2);
            System.out.println(IP2 + " is a known spammer.");
        } catch (Exception e) {
            System.out.println(IP2 + " appears legitimate");
        }

    }
}
