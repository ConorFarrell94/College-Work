import java.awt.*;
import java.applet.Applet;
import java.applet.AudioClip;
public class AudioTest extends Applet {
	 public void paint(Graphics g) {
         AudioClip clip = this.getAudioClip( this.getDocumentBase(), "Alarm.wav" );
         clip.play();
	    } 
    }   