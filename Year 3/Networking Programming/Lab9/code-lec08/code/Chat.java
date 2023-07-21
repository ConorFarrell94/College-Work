import java.rmi.*;
public interface Chat extends Remote {
     public void sendMessage(String text) throws RemoteException;
     public String getMessage() throws RemoteException;
    }
	