import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.net.*;
import java.io.*;  
public class Client extends JFrame {
     public Client (){
	     super( "Client" ); 
	     JLabel label = new JLabel("Server Data will print here");
	     Container container = getContentPane();  
	     container.setLayout( new FlowLayout() );
	     container.add(label);
	     setSize( 400, 300 );
	     setVisible( true );	
 	     try {
    	     Socket client = new Socket( "localhost",4000 ); 
		     System.out.println("Client is connected to Server");
			 ObjectInputStream in = new ObjectInputStream( client.getInputStream() );
			 while (true) {
				 String message = ( String ) in.readObject();
				 label.setText( "Message from Server: "+ message);
			}   }
		 catch (Exception ex) {
			 System.err.println(ex);
		}   }
     public static void main( String args[] ) {
		 Client application = new Client();
		 application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		}
	}


