import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class CalculatorServer implements Calculator {
     public int Add( int a, int b) {
         return a+b;}
     public int Subtract( int a, int b) {
         return a-b;}
     public int Multiply( int a, int b) {
         return a*b;}
     public static void main(String args[]) {
         try {
             CalculatorServer obj = new CalculatorServer();
             Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);
             // Bind the remote object's stub in the registry
             Registry registry = LocateRegistry.getRegistry();
             registry.bind("Calculator", stub);
             System.out.println("Server is Running");
            }
    		 catch (Exception e) {
                 System.out.println(e);
    }   }   }
    