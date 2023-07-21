import java.rmi.registry.*;
public class HelloClient {
  public static void main(String[] args) {
     try {
         Registry registry = LocateRegistry.getRegistry("localhost");
         Hello stub = (Hello) registry.lookup("Hello");
         String message = stub.sayHello();
         System.out.println(message);
        } 
	 catch (Exception e) {
        System.out.println(e);
        }
    }
}