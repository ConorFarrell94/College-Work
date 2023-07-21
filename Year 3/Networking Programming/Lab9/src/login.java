import java.rmi.*;
public interface login extends Remote {
    void sendMessage(String text) throws RemoteException;
    String getMessage() throws RemoteException;
    int verifyLogin(String username, String password) throws RemoteException;
    void modifyLogin(String username, String password) throws RemoteException;
    String getPassword() throws RemoteException;

}
