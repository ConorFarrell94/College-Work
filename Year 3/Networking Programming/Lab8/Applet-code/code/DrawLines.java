import java.awt.Graphics;
import javax.swing.JApplet; 
public class DrawLines extends JApplet {
       public void paint( Graphics g ){
           super.paint( g );
           g.drawLine( 15, 10, 210, 10 );
           g.drawLine( 15, 30, 210, 30 );         
           g.drawString( "Welcome to Java Programming", 25, 25 );
        } 
	} 
	