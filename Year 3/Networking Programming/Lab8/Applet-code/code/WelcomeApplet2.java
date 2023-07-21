import java.awt.Graphics; 
import javax.swing.JApplet; 
public class WelcomeApplet2 extends JApplet {
       public void paint( Graphics g ){
            super.paint( g );
            g.drawString( "Welcome to", 25, 25 );
            g.drawString( "Java Programming!", 25, 40 );
        } 
    } 