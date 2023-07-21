import java.rmi.registry.*;
import java.applet.Applet;
import java.awt.Graphics; 
import javax.swing.*; 
public class HelloApplet extends Applet {
     String message = "";
     public void init() {
	 try {
         Registry registry = LocateRegistry.getRegistry("localhost");
         Hello stub = (Hello) registry.lookup("Hello");
         message = stub.sayHello();
        } 
	 catch (Exception e) {
         System.out.println(e);
    }   }
	 public void paint(Graphics g) {
	     g.drawString(message, 25, 25);
    }   }
	