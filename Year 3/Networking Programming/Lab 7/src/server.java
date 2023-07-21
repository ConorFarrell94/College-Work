import java.net.*;
import java.io.*;
public class server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000, 10); // create ServerSocket
            System.out.println("Server is Running on port 5000");
            Socket connection = server.accept();
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            String message;
            int num1;
            //noinspection InfiniteLoopStatement
            while (true) {
                num1 = (int) in.readObject();
                switch (num1) {
                    case 1 -> {
                        message = "Please enter 2 numbers";
                        out.writeObject(message);
                        int num2 = (int) in.readObject();
                        int num3 = (int) in.readObject();
                        int result;
                        result = num2 + num3;
                        out.writeObject(result);
                        break;
                    }
                    case 2 -> {
                        message = "Please enter 1 numbers";
                        out.writeObject(message);
                        double num4 = (double) in.readObject();
                        double squareAns = Math.sqrt(num4);
                        out.writeObject(squareAns);
                        break;
                    }
                    case 3 -> {
                        message = "Please enter 2 numbers";
                        out.writeObject(message);
                        int num5 = (int) in.readObject();
                        int num6 = (int) in.readObject();
                        int powerAns = (int) Math.pow(num5, num6);
                        out.writeObject(powerAns);
                        break;
                    }
                    case 0 -> {
                        out.writeChars("Connection closing, Goodbye");
                        connection.close();
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + num1);
                }
            }
        }
            catch (Exception ex) {
                System.out.println("Error");
        }
    }
}
