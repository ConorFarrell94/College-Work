import java.rmi.registry.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {

        String ClientUser;
        String ClientPassword;

        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            Login stub = (Login) registry.lookup("Login");
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println("Username=");
                ClientUser = input.nextLine();
                System.out.println("Enter Password");
                ClientPassword = input.nextLine();
                stub.verifyLogin(ClientUser, ClientPassword);
                if (stub.verifyLogin(ClientUser, ClientPassword) == 1) {
                    System.out.println("New username=");
                    ClientUser = input.nextLine();
                    System.out.println("New password=");
                    ClientPassword = input.nextLine();
                    stub.modifyLogin(ClientUser, ClientPassword);

                    System.out.println(stub.getPassword());
                } else {
                    System.out.println("Incorrect");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

