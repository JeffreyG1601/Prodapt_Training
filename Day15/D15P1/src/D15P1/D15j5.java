package D15P1;
import java.io.*;
import java.util.*;

public class D15j5 {

    // === Checked Exceptions Examples ===
    static void checked_FileNotFound() throws FileNotFoundException {
        FileReader fr = new FileReader("non_existing_file.txt"); // FileNotFoundException
    }

    static void checked_SQLExample() throws Exception {
        // Pretend JDBC connection
        throw new Exception("Database connection failed!"); // Simulating SQLException
    }

    // === Unchecked Exceptions Examples ===
    static void unchecked_Arithmetic() {
        int result = 10 / 0; // ArithmeticException
    }

    static void unchecked_NullPointer() {
        String str = null;
        System.out.println(str.length()); // NullPointerException
    }

    static void unchecked_ArrayIndex() {
        int[] arr = {1, 2, 3};
        System.out.println(arr[5]); // ArrayIndexOutOfBoundsException
    }

    public static void main(String[] args) {
        // Checked Exception handling
        try {
            checked_FileNotFound();
        } catch (FileNotFoundException e) {
            System.out.println("Checked Exception: " + e);
        }

        try {
            checked_SQLExample();
        } catch (Exception e) {
            System.out.println("Checked Exception: " + e);
        }

        // Unchecked Exception handling
        try {
            unchecked_Arithmetic();
        } catch (ArithmeticException e) {
            System.out.println("Unchecked Exception: " + e);
        }

        try {
            unchecked_NullPointer();
        } catch (NullPointerException e) {
            System.out.println("Unchecked Exception: " + e);
        }

        try {
            unchecked_ArrayIndex();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unchecked Exception: " + e);
        }

        System.out.println("Program execution continues after handling all exceptions.");
    }
}
