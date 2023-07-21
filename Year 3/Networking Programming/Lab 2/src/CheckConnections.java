import java.net.URL;

public class CheckConnections {

    public static void main (String[] args) {
        testProtocol("https://www.tudublin.ie/");
        testProtocol("https://www.tudublin.ie/");
        testProtocol("ftp://https://www.tudublin.ie/");
        testProtocol("mailto:C19704439@mytudublin.ie");
    }

    public static void testProtocol(String url){
        try {
            URL u = new URL(url);
            System.out.println(u.getProtocol() + " is supported");
        }
        catch (Exception ex) {
            System.out.println(url + " is not supported");
        }
    }
}

