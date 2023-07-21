import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
public class ChatServer implements Chat {
        public void sendMessage(String s) throws RemoteException {
          System.out.println("Client says  "+s);
		}
        public String getMessage() throws RemoteException {
         Scanner input = new Scanner(System.in);
		 System.out.println("Enter the Server message");
		 String message = input.nextLine();
		 return message;
        }
     public static void main(String args[]) {
         try {
             ChatServer obj = new ChatServer();
             Chat stub = (Chat) UnicastRemoteObject.exportObject(obj, 0);
             Registry registry = LocateRegistry.getRegistry();
             registry.bind("Chat", stub);
			 System.out.println("Server is Running");
            }
		 catch (Exception e) {
             System.out.println(e);
    }   }   }
    
