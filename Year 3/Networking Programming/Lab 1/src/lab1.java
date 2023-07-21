import java.util.Scanner;

public class lab1 {

    public static void main(String[] args) {
        System.out.println("Hello World");

        int number1, number2, sum;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first integer:");
        number1 = input.nextInt();
        System.out.println("Enter second integer:");
        number2 = input.nextInt();
        sum = number1 + number2;
        input.close();
        System.out.println("Sum is: " + sum);

        // compare integers using if statement & relational operators
        if (number1 % 2 == 0) {
            System.out.println("Number 1 [ " + number1 + " ] is even");
        }
        else {
            System.out.println("Number 1 [ " + number1 + " ] is odd");
        }
        
    }
}
