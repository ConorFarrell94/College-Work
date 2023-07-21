import java.rmi.*;

public interface Login extends Remote {


    int verifyLogin(String username, String password) throws RemoteException;

    void modifyLogin(String username, String password) throws RemoteException;

    String getPassword() throws RemoteException;

}
