import java.util.Scanner;

public class Calculator {
    private static void terminate() {
        System.out.println("Invalid input entered. Terminating...");
        return;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("List of operations: add subtract multiply divide alphabetize");
        System.out.println("Enter an operation:");
        String operation = input.nextLine().toLowerCase();

        switch (operation) {
            case "add":
            case "subtract":
                System.out.println("Enter two integers:");
                if (input.hasNextInt() && input.hasNextInt()) {
                    int num1 = input.nextInt();
                    int num2 = input.nextInt();
                    if (operation.equals("add")) {
                        System.out.println("Answer: " + (num1 + num2));
                    } else {
                        System.out.println("Answer: " + (num1 - num2));
                    }
                } else {
                    terminate();
                }
                break;
            case "multiply":
            case "divide":
                System.out.println("Enter two doubles:");
                if (input.hasNextDouble() && input.hasNextDouble()) {
                    double num1 = input.nextDouble();
                    double num2 = input.nextDouble();
                    if (operation.equals("multiply")) {
                        System.out.printf("Answer: %.2f\n", (num1 * num2));
                    } else {
                        if (num2 == 0) {
                            terminate();
                        }
                        System.out.printf("Answer: %.2f\n", (num1 / num2));
                    }
                } else {
                    terminate();
                }
                break;
            case "alphabetize":
                System.out.println("Enter two words:");
                String word1 = input.next();
                String word2 = input.next();
                int compare = word1.compareToIgnoreCase(word2);
                if (compare < 0) {
                    System.out.println("Answer: " + word1 + " comes before " + word2 + " alphabetically");
                } else if (compare > 0) {
                    System.out.println("Answer: " + word2 + " comes before " + word1 + " alphabetically");
                } else {
                    System.out.println("Answer: Chicken or Egg.");
                }
                break;
            default:
                terminate();
                break;
        }
    }
}
