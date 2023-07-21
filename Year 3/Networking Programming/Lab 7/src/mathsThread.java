import java.lang.Math;
import java.util.Scanner;

class Sin extends Thread {
    public double deg;
    public double res;
    public Sin(int degree) {
        deg = degree;
    }
    public void run() {
        System.out.println("Starting to execute SIN of - " + deg);
        double Deg2Rad = Math.toRadians(deg);
        res = Math.sin(Deg2Rad);
        System.out.println("Sin Complete - Result = " + res);
    }
}
class Cos extends Thread {
    public double deg;
    public double res;
    public Cos(int degree) {
        deg = degree;
    }
    public void run() {
        System.out.println("Starting to execute COS of - " + deg);
        double Deg2Rad = Math.toRadians(deg);
        res = Math.cos(Deg2Rad);
        System.out.println("Cos Complete - Result = " + res);
    }
}
class Tan extends Thread {
    public double deg;
    public double res;
    public Tan(int degree) {
        deg = degree;
    }
    public void run() {
        System.out.println("Starting to execute TAN of - " + deg);
        double Deg2Rad = Math.toRadians(deg);
        res = Math.tan(Deg2Rad);
        System.out.println("Tan Complete - Result = " + res);
    }
}
public class mathsThread {
    public static void main(String[] args) {
        Scanner getDeg = new Scanner(System.in);
        System.out.println("Enter value for SIN :");
        Sin st = new Sin(getDeg.nextInt());
        System.out.println("Enter value for COS :");
        Cos ct = new Cos(getDeg.nextInt());
        System.out.println("Enter value for TAN :");
        Tan tt = new Tan(getDeg.nextInt());
        getDeg.close();
        st.start();
        ct.start();
        tt.start();
        try { 
            st.join();
            ct.join();
            tt.join();
            double z = st.res + ct.res + tt.res;
            System.out.println("Sum of SIN COS TAN = " + z);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
