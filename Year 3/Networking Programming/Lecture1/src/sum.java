import java.util.Scanner;

public class sum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number1, number2, sum;
        System.out.println("Enter first integer:");
        number1 = input.nextInt();
        System.out.println("Enter second integer:");
        number2 = input.nextInt();
        sum = number1 + number2;
        System.out.println("Sum is: " + sum);
    }

}
