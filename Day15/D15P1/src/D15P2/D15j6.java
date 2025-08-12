package D15P2;
import java.io.*;
import java.util.*;

// ===== Custom Exception =====
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class D15j6 {

    // ==== Checked Exception Example ====
    static void loadAccountData() throws IOException {
        File file = new File("account_data.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("Account data file not found!");
        }
        // Simulate reading file...
    }

    // ==== Unchecked Exceptions Examples ====
    static void causeNullPointer() {
        String name = null;
        System.out.println(name.length()); // NullPointerException
    }

    static void causeArithmetic() {
        int result = 10 / 0; // ArithmeticException
    }

    static void causeArrayIndex() {
        int[] arr = {1, 2, 3};
        System.out.println(arr[5]); // ArrayIndexOutOfBoundsException
    }

    // ==== Custom Exception Scenario ====
    static void withdraw(double balance, double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Withdrawal denied! Not enough balance.");
        }
        System.out.println("Withdrawal successful! Remaining balance: " + (balance - amount));
    }

    public static void main(String[] args) {
        // 1️⃣ Checked Exception
        try {
            loadAccountData();
        } catch (IOException e) {
            System.out.println("Checked Exception caught: " + e.getMessage());
        }

        // 2️⃣ Unchecked Exceptions
        try {
            causeNullPointer();
        } catch (NullPointerException e) {
            System.out.println("Unchecked Exception caught: " + e);
        }

        try {
            causeArithmetic();
        } catch (ArithmeticException e) {
            System.out.println("Unchecked Exception caught: " + e);
        }

        try {
            causeArrayIndex();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unchecked Exception caught: " + e);
        }

        // 3️⃣ Custom Exception
        try {
            withdraw(5000, 7000);
        } catch (InsufficientBalanceException e) {
            System.out.println("Custom Exception caught: " + e.getMessage());
        }

        System.out.println("Program finished without crashing!");
    }
}
