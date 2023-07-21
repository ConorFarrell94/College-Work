import java.net.*;
import java.io.*;
import java.util.Scanner;

class name extends Thread {
    public String name;
    Scanner input = new Scanner(System.in);
    public void run() {
        System.out.println("------ Name Input Start ------");
        System.out.println("Whats your name?");
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            name = input.next();
            out.writeObject(name);
            input.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("------ Name Input End ------");
    }
}

class p_number extends Thread {
    public Integer p_number;
    Scanner input = new Scanner(System.in);
    public void run() {
        System.out.println("------ Number Input Start ------");
        System.out.println("Whats your phone number?");
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            p_number = input.nextInt();
            out.writeObject(p_number);
            input.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("------ Number Input End ------");
    }
}

class location extends Thread {
    public String location;
    Scanner input = new Scanner(System.in);
    public void run() {
        System.out.println("------ Location Input Start ------");
        System.out.println("Where are you?");
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            location = input.next();
            out.writeObject(location);
            input.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("------ Location Input End ------");
    }
}

public class lab7_client {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 5000);
            System.out.println("Client is connected to Server");

            name name1 = new name();
            p_number p_number1 = new p_number();
            location location1 = new location();

            name1.start();
            p_number1.start();
            location1.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
