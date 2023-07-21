import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
public class ButtonTest extends Applet implements ActionListener {
 	 public void init(){
 	     Button b = new Button("Test Button");
 	     add(b);
         b.addActionListener(this); 
	    }
     public void actionPerformed(ActionEvent e) {
         JOptionPane.showMessageDialog( null, "Test Button is Clicked" );
    }   }
	