import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class HelloServer implements Hello {
     public String sayHello() {
         return "Hello, world!";
        }
     public static void main(String args[]) {
         try {
             HelloServer obj = new HelloServer();
             Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
             // Bind the remote object's stub in the registry
             Registry registry = LocateRegistry.getRegistry();
             registry.bind("Hello", stub);
             System.out.println("Server is Running");
            }
		 catch (Exception e) {
             System.out.println(e);
    }   }   }
	