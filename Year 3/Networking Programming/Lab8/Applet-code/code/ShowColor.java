import java.applet.Applet;
import java.awt.*;
public class ShowColor extends Applet {
 	 public void paint(Graphics g){
	      super.paint( g );
	     g.setColor( new Color( 255, 0, 0 ) );
	     g.fillRect( 25, 25, 100, 20 ); // x=25,y=25,100 wide,20 tall 
	     g.drawString( "Red Rectangle " , 130, 40 ); 	
	     g.setColor( new Color( 0.0f, 1.0f, 0.0f ) );
	     g.fillRect( 25, 50, 100, 20 );
	     g.drawString( "Green Rectangle " , 130, 65 );
	     g.setColor( Color.blue );
 	     g.fillRect( 25, 75, 100, 20 );
	     g.drawString( "Blue Rectangle ", 130, 90 );
    }   }
	