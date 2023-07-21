import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServerThread extends Thread {
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private String valueForFirstKey;
    private Socket socket;
    private static ArrayList<ServerThread> threadList;
    private static String finalWinner;
    private PrintWriter output;
    private static Map<String, Integer> items;
    private static Boolean sold = false;

    public static void printToALlClients(String outputString) {
        try {
            for (ServerThread sT : threadList) {
                sT.output.println(outputString);
            }
        } catch (Exception ignored) {

        }
    }
    public static Map<String, Integer> printAllItems() {
        Map<String, Integer> itemList = new HashMap<>();
        server.itemList.forEach((key, value) -> itemList.put(key, value));
        return itemList;
    }

    public static void notifySold(String outputString) {
        for (ServerThread sT : threadList) {
            sT.output.println(finalWinner + outputString);
        }
    }

    public static Boolean detectSold() {
        return sold;
    }

    public static void resetSold() {
        sold = false;
    }

    public ServerThread(Socket socket, ArrayList<ServerThread> threads, Map<String, Integer> itemList) {
        this.socket = socket;
        this.threadList = threads;
        String firstKey = server.getKey();
        this.valueForFirstKey = server.getValue();
    }

    @Override
    public void run() {
        try {
            // Reading the input from Client
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // returning the output to the client : true statement is to flush the buffer
            // otherwise we have to do it manually
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("----------------------------- MENU -----------------------------");
            output.println("Type 'menu' to show main menu");
            output.println("Type 'item' to show what is being sold now");
            output.println("Type 'list' to show what all the items currently in auction");
            output.println("Type 'bid' followed by amount to place a bid ( bid 100 )");
            output.println("Type 'say' to send a chat message");
            output.println("Type 'exit' to leave");
            output.println("----------------------------------------------------------------");
            Date date = new Date(System.currentTimeMillis());
            output.println(formatter.format(date));
            output.println("CURRENT ITEM : " + server.getKey() + " | PRICE : " + server.getValue());
            output.println("Enter your name to begin");
            // infinite loop for server
            while (true) {
                String outputString = input.readLine();
                if (outputString.contains("list")) {
                    output.println(printAllItems());
                }
                if (outputString.contains("item")) {
                    output.println(server.getKey());
                    output.println(server.getValue());
                }
                if (outputString.contains("bid")) {
                    server.timer("restart");
                    String tempStr = outputString;
                    String bidder = tempStr.substring(tempStr.indexOf("(") + 1, tempStr.indexOf(")"));
                    finalWinner = bidder;
                    if (outputString.contains("-")) {
                        output.println("Cannot bid negative numbers");
                    }
                    else {
                        outputString = outputString.replaceAll("[^0-9]", " ");
                        outputString = outputString.replaceAll(" +", " ");
                        output.println(outputString);
                        valueForFirstKey = server.getValue() + outputString;
                        String temp = valueForFirstKey;
                        String numbers[] = temp.split("\\s+"); // Split the input string.
                        int sum = 0;
                        for (String number : numbers) { // loop through all the number in the string array
                            Integer n = Integer.parseInt(number); // parse each number
                            sum += n; // sum the numbers
                        }
                        valueForFirstKey = String.valueOf(sum);
                        // output.println("New Price : " + valueForFirstKey);
                        printToALlClients(bidder + " just bid " + valueForFirstKey);
                        printToALlClients("Bid timer reset!");
                        // items.put(firstKey, Integer.valueOf(valueForFirstKey));
                        server.insertItem(server.getKey(), Integer.valueOf(valueForFirstKey));
                        sold = true;
                    }
                }
                if (outputString.contains("menu")) {
                    output.println("----------------------------- MENU -----------------------------");
                    output.println("Type 'menu' to show main menu");
                    output.println("Type 'item' to show what is being sold now");
                    output.println("Type 'list' to show what all the items currently in auction");
                    output.println("Type 'bid' followed by amount to place a bid ( bid 100 )");
                    output.println("Type 'say' to send a chat message");
                    output.println("Type 'exit' to leave");
                    output.println("----------------------------------------------------------------");
                }
                ;
                // if user types exit command
                if (outputString.equals("exit")) {
                    break;
                }
                // printToALlClients(outputString);
                // output.println("Server says " + outputString);
                // System.out.println(threadList);
                System.out.println("----------------------------------------------------------------");
                System.out.println("Server received " + outputString);
            }
        } catch (Exception e) {
            System.out.println("Error occurred " + Arrays.toString(e.getStackTrace()));
        }
    }

}
