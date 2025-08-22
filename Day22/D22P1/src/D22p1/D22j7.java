package D22p1;

import java.util.TreeSet;

public class D22j7 {
    public static void main(String[] args) {
        // Creating a TreeSet of Strings
        TreeSet<String> fruits = new TreeSet<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Orange");
        fruits.add("Banana"); // Duplicate, ignored

        System.out.println("Fruits (sorted): " + fruits);

        // Removing an element
        fruits.remove("Mango");
        System.out.println("After removing Mango: " + fruits);

        // Checking size
        System.out.println("Number of fruits: " + fruits.size());

        // Checking if set contains an element
        System.out.println("Contains Orange? " + fruits.contains("Orange"));
        System.out.println("Contains Grapes? " + fruits.contains("Grapes"));

        // Getting first and last elements
        System.out.println("First fruit: " + fruits.first());
        System.out.println("Last fruit: " + fruits.last());

        // Iterating through elements (always sorted order)
        System.out.println("Fruits one by one (sorted):");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Clearing the set
        fruits.clear();
        System.out.println("After clearing: " + fruits);
    }
}
