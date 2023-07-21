import java.applet.Applet;
import java.awt.*;
public class ComponentApplet extends Applet {
 	 public void init(){
 	     Button b = new Button("Test Button");
 	     add(b);
         Checkbox cb = new Checkbox("Test Checkbox");
 	     add(cb);
         CheckboxGroup cbg = new CheckboxGroup();
 	     add(new Checkbox("CB Item 1", cbg, false));
 	     add(new Checkbox("CB Item 2", cbg, false));
 	     add(new Checkbox("CB Item 3", cbg, true));
 	     Choice choice = new Choice();
 	     choice.addItem("Choice Item 1");
		 choice.addItem("Choice Item 2");
 	     choice.addItem("Choice Item 3");
	     add(choice);
		 Label l = new Label("Test Label");
 	     add(l);
         TextField t = new TextField("Test TextField",30);
 	     add(t);
 	    }
    }
	