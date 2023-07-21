import java.awt.Graphics;
import java.applet.Applet;
public class Counter extends Applet {
     private int count = 0;
	 public void paint(Graphics g){
      	 count++;
      	 g.drawString("Hello World! (updated " + count + " times)", 20, 20);
		}
	}
	