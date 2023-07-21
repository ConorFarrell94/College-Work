import java.net.*;
import java.io.*;
import java.util.Scanner;
public class client {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 5000);
            System.out.println("Client is connected to Server");
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            Scanner input = new Scanner(System.in);
            int num1;
            //noinspection InfiniteLoopStatement
            while(true) {
                System.out.println("Enter your Message");
                num1 = input.nextInt();
                out.writeObject(num1);
                String message = ( String ) in.readObject();
                System.out.println ("Data from Server:  " + message);
                switch (num1) {
                    case 1 -> {
                        int num2 = input.nextInt();
                        int num3 = input.nextInt();
                        out.writeObject(num2);
                        out.writeObject(num3);
                        int result = (int) in.readObject();
                        System.out.println("Data from Server:  " + result);
                    }
                    case 2 -> {
                        double num4 = input.nextDouble();
                        out.writeObject(num4);
                        double squareAns = (double) in.readObject();
                        System.out.println("Data from Server:  " + squareAns);
                    }
                    case 3 -> {
                        int num5 = input.nextInt();
                        int num6 = input.nextInt();
                        out.writeObject(num5);
                        out.writeObject(num6);
                        int powerAns = (int) in.readObject();
                        System.out.println("Data from Server:  " + powerAns);
                    }
                    case 0 -> {
                        out.writeObject(num1);
                        System.out.println("Connection closing, Goodbye");
                        client.close();
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + num1);
                }
                }
            }
        catch(Exception ex){
            System.out.println("Error");
        }
    }

    public static OutputStream getOutputStream() {
        return null;
    }

    public static InputStream getInputStream() {
        return null;
    }
}
