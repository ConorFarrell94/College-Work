import java.rmi.registry.*;
import java.util.*;
public class ChatClient {
     public static void main(String[] args) {
     try {
         Registry registry = LocateRegistry.getRegistry("localhost");
         Chat stub = (Chat) registry.lookup("Chat");
		 Scanner input = new Scanner(System.in);
		 while (true) {
		     System.out.println("Enter the Client message");
		     String message = input.nextLine();
             stub.sendMessage(message);
             message = stub.getMessage();
		     System.out.println(message);
	    }   } 
	 catch (Exception e) {
         System.out.println(e);
        }
    }
}