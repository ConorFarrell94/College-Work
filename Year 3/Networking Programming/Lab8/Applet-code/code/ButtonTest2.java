import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
public class ButtonTest2 extends Applet implements ActionListener {
     Button b1,b2;
 	 public void init(){
 	     b1 = new Button("Button 1");
 	     b2 = new Button("Button 2");
         add(b1);
 	     add(b2);
         b1.addActionListener(this); 
         b2.addActionListener(this); 
	    }
     public void actionPerformed(ActionEvent e) {
         if ( e.getSource() == b1)
             JOptionPane.showMessageDialog( null, "Button1 is Clicked" );
         if ( e.getSource() == b2)
             JOptionPane.showMessageDialog( null, "Button2 is Clicked" );
    }   }
	