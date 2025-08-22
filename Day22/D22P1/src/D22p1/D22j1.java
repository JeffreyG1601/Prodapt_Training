package D22p1;

import java.util.ArrayList;

public class D22j1 {
    public static void main(String[] args) {
        // Creating an ArrayList of Strings
        ArrayList<String> fruits = new ArrayList<>();

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
        fruits.remove("Mango");    // Removes Mango by value
        fruits.remove(0);          // Removes Apple by index
        System.out.println("After removals: " + fruits);

        // Checking size
        System.out.println("Number of fruits: " + fruits.size());

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
