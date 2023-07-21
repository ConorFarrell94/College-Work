import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
public class ButtonTest3 extends Applet implements ActionListener {
     Button b1;
     TextField t1;
 	 public void init(){
 	     b1 = new Button("Button 1");
 	     t1 = new TextField(30);
         add(b1);
 	     add(t1);
	     b1.addActionListener(this); 
        }
     public void actionPerformed(ActionEvent e) {
         String message  = t1.getText();
         JOptionPane.showMessageDialog( null, message );
    }   }
	