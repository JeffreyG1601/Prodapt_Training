package D22p1;

import java.util.Vector;

public class D22j3 {
    public static void main(String[] args) {
        // Creating a Vector of Strings
        Vector<String> fruits = new Vector<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Orange");

        System.out.println("Fruits: " + fruits);

        // Accessing elements by index
        System.out.println("First fruit: " + fruits.get(0));

        // Modifying an element
        fruits.set(1, "Grapes");  // Replaces Banana with Grapes
        System.out.println("After modification: " + fruits);

        // Removing elements
        fruits.remove("Mango");   // Removes by value
        fruits.remove(0);         // Removes by index
        System.out.println("After removals: " + fruits);

        // Checking size and capacity
        System.out.println("Size: " + fruits.size());
        System.out.println("Capacity: " + fruits.capacity());

        // Checking if list contains an item
        System.out.println("Contains Orange? " + fruits.contains("Orange"));

        // Iterating through elements
        System.out.println("Fruits one by one:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Clearing all elements
        fruits.clear();
        System.out.println("After clearing: " + fruits);
    }
}

