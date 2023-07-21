import java.awt.*;
import java.applet.Applet;
public class ImageTest extends Applet {
	 public void paint(Graphics g) {
         Image myImage = this.getImage( this.getDocumentBase(), "dit.gif" );
         g.drawImage( myImage, 10, 10, this);
        }
	}