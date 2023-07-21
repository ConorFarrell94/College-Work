import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.net.*;
import java.io.*;
public class Server extends JFrame implements ActionListener {
     JButton b1;
     JTextField t1;
     ServerSocket server = null;
     Socket connection; 
     ObjectOutputStream out;
      public Server(){
         super( "Server" ); 
	     Container container = getContentPane();  
	     container.setLayout( new FlowLayout() );
	     b1 = new JButton("Send to Client");
 	     t1 = new JTextField(30);
 	     container.add(b1);
 	     container.add(t1);              
		 setSize( 400, 300 );
		 setVisible( true );
         b1.addActionListener(this); 
		 try {
			 server = new ServerSocket( 4000, 10); // create ServerSocket
			 System.out.println("Server is Running on port 4000");
			 connection = server.accept();
			 out = new ObjectOutputStream( connection.getOutputStream() );	
			} 
		 catch (Exception ex) {
			 System.err.println(ex);
		}   }

     public void actionPerformed(ActionEvent e) {
         try {
		     String message  = t1.getText();
			 out.writeObject(message);
			 System.out.println("Data send to client");
			}
		 catch (Exception ex) {
		 System.err.println(ex);} 
		}
	 public static void main( String args[] ){
		 Server application = new Server();
		 application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	}   }
	