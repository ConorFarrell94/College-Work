import java.rmi.registry.*;
import java.util.*;

public class client {
    public static void main(String[] args) {

        String cUser;
        String cPassword;

        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            login stub = (login) registry.lookup("login");
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println("Enter the Client message");
                String message = input.nextLine();
                stub.sendMessage(message);
                message = stub.getMessage();
                System.out.println(message);

                System.out.println("Enter username : ");
                cUser = input.nextLine();
                System.out.println("Enter password : ");
                cPassword = input.nextLine();
                stub.verifyLogin(cUser,cPassword);
                if (stub.verifyLogin(cUser,cPassword) == 1)
                {
                    System.out.println("Enter new username : ");
                    cUser = input.nextLine();
                    System.out.println("Enter new password : ");
                    cPassword = input.nextLine();
                    stub.modifyLogin(cUser,cPassword);

                    System.out.println(stub.getPassword());
                }
                else {
                    System.out.println("Wrong. Try again");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

