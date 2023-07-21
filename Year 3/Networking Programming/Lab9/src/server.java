import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class server implements login {

    public String username = "RMI";
    public String password = "DIT";


    public void sendMessage(String s) throws RemoteException {
        System.out.println("Client says  "+s);
    }
    public String getMessage() throws RemoteException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Server message : ");
        return input.nextLine();
    }

    public int verifyLogin(String username1, String password1) {
        if (Objects.equals(username1, username) && Objects.equals(password1, password)) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public void modifyLogin(String username2, String password2) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the new username : ");
        username = input.nextLine();
        input.close();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Enter the new password : ");
        password = input.nextLine();
        input2.close();

        System.out.println("Details successfully changed!");
    }

    public String getPassword() {
        System.out.println("Current password is " + password);
        return null;
    }

    public static void main(String[] args) {
        try {
            server obj = new server();
            login stub = (login) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("login", stub);
            System.out.println("Server is Running");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

