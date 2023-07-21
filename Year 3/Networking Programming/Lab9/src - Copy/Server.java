import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Server implements Login {

    public String username = "RMI";
    public String password = "DIT";

    public static void main(String[] args) {
        try {
            server obj = new server();
            Login stub = (Login) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Login", stub);
            System.out.println("Server is Running");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int verifyLogin(String username1, String password1) {
        if (Objects.equals(username1, username) && Objects.equals(password1, password)) {
            return 1;
        } else {
            return 0;
        }
    }

    public void modifyLogin(String username2, String password2) {
        Scanner input = new Scanner(System.in);
        System.out.println("New username=");
        username = input.nextLine();
        input.close();

        Scanner input2 = new Scanner(System.in);
        System.out.println("New password=");
        password = input.nextLine();
        input2.close();

        System.out.println("Changes made");
    }

    public String getPassword() {
        System.out.println("Your password is " + password);
        return null;
    }
}

