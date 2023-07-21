import java.net.*;

public class getIP {
    public static void main (String[] args) {
        try{
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
        }
        catch (Exception e) {
            System.out.println("Unable to find IP");
        }
    }
}