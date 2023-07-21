import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.rmi.registry.*;

public class applet extends Applet implements ActionListener{
    login stub;
    TextField text;
    Label label;
    public void init() {
        try {
            Button button = new Button("Send Data to Server");
            text = new TextField(30);
            label = new Label("Server Data will print here");
            Registry registry = LocateRegistry.getRegistry("localhost");
            stub = (login) registry.lookup("Chat");
            add(button);
            add(text);
            add(label);
            button.addActionListener(this);}
        catch (Exception e) {System.out.println(e);} }
    public void actionPerformed(ActionEvent event) {
        try {
            stub.sendMessage(text.getText());
            label.setText(stub.getMessage()); }
        catch (Exception e) {
            System.out.println(e);
        }   }   }

