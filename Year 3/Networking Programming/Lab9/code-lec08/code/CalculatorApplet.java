import java.rmi.registry.*;
import java.awt.Graphics; 
import javax.swing.*; 
public class CalculatorApplet extends JApplet {
     int sum,sub,mul;
    public void init() {
     try {
         Registry registry = LocateRegistry.getRegistry("localhost");
         Calculator stub = (Calculator) registry.lookup("Calculator");
         String firstNumber; // first string entered by user
         String secondNumber; // second string entered by user
         int number1; // first number to add,sub and mul
         int number2; // second number to add,sub and mul
         firstNumber = JOptionPane.showInputDialog("Enter first number");
         secondNumber = JOptionPane.showInputDialog("Enter second number");
         number1 = Integer.parseInt(firstNumber );
         number2 = Integer.parseInt(secondNumber );
		 sum = stub.Add(number1,number2);
         sub = stub.Subtract(number1,number2);
         mul = stub.Multiply(number1,number2);
        }
	 catch (Exception e) {
            System.out.println(e);
    }   }
 public void paint( Graphics g ){
             g.drawRect( 15, 10, 150, 70 );
	       g.drawString( "The sum is " + sum, 25, 25 );
		   g.drawString( "The sub is " + sub, 25, 45 );
		   g.drawString( "The mul is " + mul, 25, 65 );
    }   }