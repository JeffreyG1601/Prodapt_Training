package D21p1;

import java.util.*;

class Transaction {
    int id;
    String type;   // Deposit / Withdraw
    double amount;
    Date date;

    Transaction(int id, String type, double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "TxnID: " + id + ", Type: " + type + ", Amount: " + amount + ", Date: " + date;
    }
}

public class D21c5{
    private static Stack<Transaction> history = new Stack<>();
    private static double balance = 0;
    private static int txnCounter = 1;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Last Transaction");
            System.out.println("5. All Transactions");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: deposit(); break;
                case 2: withdraw(); break;
                case 3: checkBalance(); break;
                case 4: lastTransaction(); break;
                case 5: allTransactions(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Deposit
    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();
        if (amt <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        balance += amt;
        history.push(new Transaction(txnCounter++, "Deposit", amt));
        System.out.println("Deposit successful! Current Balance: " + balance);
    }

    // Withdraw
    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amt = sc.nextDouble();
        if (amt <= 0 || amt > balance) {
            System.out.println("Invalid or insufficient funds!");
            return;
        }
        balance -= amt;
        history.push(new Transaction(txnCounter++, "Withdraw", amt));
        System.out.println("Withdrawal successful! Current Balance: " + balance);
    }

    // Check balance
    private static void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    // Show last transaction
    private static void lastTransaction() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet!");
            return;
        }
        System.out.println("Last Transaction: " + history.peek());
    }

    // Show all transactions
    private static void allTransactions() {
        if (history.isEmpty()) {
            System.out.println("No transactions found!");
            return;
        }
        System.out.println("Transaction History:");
        for (Transaction t : history) {
            System.out.println(t);
        }
    }
}
