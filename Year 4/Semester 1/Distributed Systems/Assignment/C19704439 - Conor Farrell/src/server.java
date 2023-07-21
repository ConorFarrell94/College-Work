import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.Timer;

public class server extends ServerThread {
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    public static Map<String, Integer> itemList = new HashMap<>(); // hashmap for items ( item, price)
    public static Boolean sold; // checking if the item was sold or not
    public static int count = 0; // indexing for timer and itemlist
    public static int delay = 35; // delay for the timer in seconds - how long each item is on sale
    public static int countdown = delay - 1; // this needs to be set one second less than the delay
    public server(Socket socket, ArrayList<ServerThread> threads, Map<String, Integer> itemList) {
        super(socket, threads, itemList);
    } // extending ServerThread to be able to access the functions there, e.g.
      // printToALlClients

    public static synchronized void insertItem(String key, int value) {
        itemList.put(key, value);
    }

    public static synchronized String getKey() {
        String key = null;
        try {
            key = (String) itemList.keySet().toArray()[count];
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return key;
    }

    public static synchronized String getValue() {
        String firstKey = getKey();
        return String.valueOf(itemList.get(firstKey));
    }
    static Timer timeLeft = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
//            if(countdown == delay - 1 || countdown == delay) {
//                printToALlClients(delay + " seconds to place bids");
//            }
            if (countdown == 0) {
                countdown = delay - 1;
            }
            if (countdown == 30) {
                printToALlClients("30 seconds remaining!");
            }
            if (countdown == 20) {
                printToALlClients("20 seconds remaining!");
            }
            if (countdown == 10) {
                printToALlClients("10 seconds remaining!");
            }
            if (countdown == 5) {
                printToALlClients("5 seconds remaining!");
            }

//            System.out.println(countdown);
            countdown = countdown - 1;
        }
    });
    static ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            timeLeft.start();
            Date date = new Date(System.currentTimeMillis());
            if (itemList.isEmpty()) {
                ServerThread.printToALlClients("All items sold - Auction ending");
                System.out.println("All items sold - Auction ending");
                timeLeft.stop();
                System.exit(0);
            } else {
                if (ServerThread.detectSold()) {
                    ServerThread.resetSold();
                    System.out.println("----------------------------------------------------------------");
                    System.out.println(formatter.format(date));
                    System.out.println("SOLD : " + getKey() + " | PRICE : " + getValue());
                    ServerThread.notifySold(" won item : " + getKey() + " at price : " + getValue());
                    itemList.remove(getKey());
                } else if (!ServerThread.detectSold()) {
                    timeLeft.restart();
                    System.out.println("----------------------------------------------------------------");
                    System.out.println(formatter.format(date));
                    System.out.println("NO BIDS");

                    ServerThread.printToALlClients("----------------------------------------------------------------");
                    ServerThread.printToALlClients(formatter.format(date));
                    ServerThread.printToALlClients("NO BIDS");
                    count = count + 1;
                    // without this if statement the last auctioned item was always "null" before
                    // reset
                    if (count == itemList.size() + 1) {
                        count = 0;
                    }
                }
            }
            if (getKey() != null) {
                System.out.println("----------------------------------------------------------------");
                System.out.println(formatter.format(date));
                System.out.println("NEXT ITEM : " + getKey() + " | PRICE : " + getValue());
                System.out.println(delay + " seconds to place bids");

                ServerThread.printToALlClients("----------------------------------------------------------------");
                ServerThread.printToALlClients(formatter.format(date));
                ServerThread.printToALlClients("NEXT ITEM : " + getKey() + " | PRICE : " + getValue());
                ServerThread.printToALlClients(delay + " seconds to place bids");
            } else if (getKey() == null) {
                System.out.println("----------------------------------------------------------------");
                System.out.println("Restarting Item Queue");

                ServerThread.printToALlClients("----------------------------------------------------------------");
                ServerThread.printToALlClients("Restarting Item Queue");
                count = -1;
            }
        timeLeft.restart();
        }
    };
    static Timer timer = new Timer(delay*1000, taskPerformer);

    public static synchronized void timer(String arg) {
        // restart the timer if a bid is placed, otherwise carry on
        if (Objects.equals(arg, "restart")) {
            timer.restart();
            countdown = delay - 1;
            timeLeft.restart();
        } else {
            timer.setRepeats(true);
            timer.start();
        }
    }

    public static void main(String[] args) {

        insertItem("Tapestry", 250);
        insertItem("Ancient Fossil", 600);
        insertItem("Used Car", 14000);
        insertItem("Gaming PC", 900);
        insertItem("Collectors Watch", 400);

        // using serversocket as argument to automatically close the socket
        // the port number is unique for each server

        // list to add all the clients thread
        ArrayList<ServerThread> threadList = new ArrayList<>();

        try (ServerSocket serversocket = new ServerSocket(5000)) {
            Date date = new Date(System.currentTimeMillis());
            System.out.println("----------------------------------------------------------------");
            System.out.println(formatter.format(date));
            System.out.println("CURRENT ITEM : " + getKey() + " | PRICE : " + getValue());
            timer(null);


            while (true) {
                Socket socket = serversocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadList, itemList);
                // starting the thread
                threadList.add(serverThread);
                serverThread.start();
                try {
                    Scanner scanner = new Scanner(System.in);
                    String userInput = scanner.nextLine();
                    if (userInput.contains("insert")) {
                        System.out.println("item name to add to auction : ");
                        String item = scanner.nextLine();
                        System.out.println("price for item : ");
                        int price = Integer.parseInt(scanner.nextLine());
                        insertItem(item, price);
                        System.out.println(item + " added to auction!");
                        ServerThread.printToALlClients(item + " added to auction!");
                    }
                } catch (Exception ignored) {
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred in main: " + Arrays.toString(e.getStackTrace()));
        }
    }
}