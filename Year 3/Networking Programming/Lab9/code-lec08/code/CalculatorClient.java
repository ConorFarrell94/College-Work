import java.rmi.registry.*;
public class CalculatorClient {
     public static void main(String[] args) {
		 try {
             Registry registry = LocateRegistry.getRegistry("localhost");
             Calculator stub = (Calculator) registry.lookup("Calculator");
             System.out.println("Addition  "+ stub.Add(10,5));
             System.out.println("Subtraction  "+ stub.Subtract(10,5));
             System.out.println("Multiply  "+ stub.Multiply(10,5));
   		    }
		 catch (Exception e) {
             System.out.println(e);
	}   }   }
 