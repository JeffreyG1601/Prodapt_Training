package D15P1;

import java.io.*;

public class D15j2 {

    // Method that demonstrates a Checked Exception
    static void readFile(String fileName) throws IOException { // throws = declare checked exception
        FileReader fr = new FileReader(fileName); // May throw FileNotFoundException
        BufferedReader br = new BufferedReader(fr);
        System.out.println("First line: " + br.readLine());
        br.close();
    }

    // Method that demonstrates an Unchecked Exception
    static void divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed!"); // throw = manually throw exception
        }
        System.out.println("Result: " + (a / b));
    }

    public static void main(String[] args) {
        try {
            // ✅ Unchecked Exception example
            divide(10, 0); // This will throw ArithmeticException

            // ✅ Checked Exception example
            readFile("non_existing_file.txt"); // This will throw FileNotFoundException

        } catch (ArithmeticException e) {
            System.out.println("Caught Unchecked Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Caught Checked Exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block always runs (cleanup code goes here).");
        }

        System.out.println("Program continues after handling exceptions...");
    }
}
