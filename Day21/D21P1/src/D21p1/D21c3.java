package D21p1;

import java.util.*;

class Item {
    int id;
    String name;
    int quantity;
    double price;

    Item(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    double getTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Qty: " + quantity + ", Price: " + price + ", Total: " + getTotal();
    }
}

public class D21c3 {
    private static LinkedList<Item> cart = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Shopping Cart ---");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Display Cart");
            System.out.println("4. Total Cost");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addItem(); break;
                case 2: removeItem(); break;
                case 3: displayCart(); break;
                case 4: totalCost(); break;
                case 5: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Add item to cart
    private static void addItem() {
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Item it : cart) {
            if (it.id == id) {
                System.out.println("Item already exists! Increasing quantity.");
                System.out.print("Enter quantity to add: ");
                int qty = sc.nextInt();
                it.quantity += qty;
                return;
            }
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        cart.add(new Item(id, name, qty, price));
        System.out.println("Item added to cart!");
    }

    // Remove item
    private static void removeItem() {
        System.out.print("Enter Item ID to remove: ");
        int id = sc.nextInt();
        Iterator<Item> it = cart.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item.id == id) {
                it.remove();
                System.out.println("Item removed successfully!");
                return;
            }
        }
        System.out.println("Item not found!");
    }

    // Display all items
    private static void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("Your Cart:");
        for (Item it : cart) {
            System.out.println(it);
        }
    }

    // Calculate total cost
    private static void totalCost() {
        double total = 0;
        for (Item it : cart) {
            total += it.getTotal();
        }
        System.out.println("Total Cart Value: " + total);
    }
}
