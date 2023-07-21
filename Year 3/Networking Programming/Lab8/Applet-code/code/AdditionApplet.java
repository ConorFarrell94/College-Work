import java.awt.Graphics; 
import javax.swing.*; 
public class AdditionApplet extends JApplet {
     double sum;  
     public void init(){
         String firstNumber; // first string entered by user
         String secondNumber; // second string entered by user
         double number1; // first number to add
         double number2; // second number to add
         firstNumber = JOptionPane.showInputDialog("Enter first number");
         secondNumber = JOptionPane.showInputDialog("Enter second number");
         number1 = Double.parseDouble( firstNumber );
         number2 = Double.parseDouble( secondNumber );
         sum = number1 + number2;
        }
     public void paint( Graphics g ){
           super.paint( g );
           g.drawRect( 15, 10, 270, 20 );
	       g.drawString( "The sum is " + sum, 25, 25 );
        } 
    } 