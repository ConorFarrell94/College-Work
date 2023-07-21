import java.rmi.*;
public interface Calculator extends Remote {
     int Add (int a, int b) throws RemoteException;
     int Subtract (int a, int b) throws RemoteException;
     int Multiply(int a, int b) throws RemoteException; 
    }