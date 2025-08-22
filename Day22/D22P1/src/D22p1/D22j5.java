package D22p1;

import java.util.HashSet;

public class D22j5 {
    public static void main(String[] args) {
        // Creating a HashSet of Strings
        HashSet<String> fruits = new HashSet<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Orange");
        fruits.add("Banana"); // Duplicate, will not be added

        System.out.println("Fruits: " + fruits);

        // Removing an element
        fruits.remove("Mango");
        System.out.println("After removing Mango: " + fruits);

        // Checking size
        System.out.println("Number of fruits: " + fruits.size());

        // Checking if set contains an element
        System.out.println("Contains Orange? " + fruits.contains("Orange"));
        System.out.println("Contains Grapes? " + fruits.contains("Grapes"));

        // Iterating through elements
        System.out.println("Fruits one by one:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Clearing the set
        fruits.clear();
        System.out.println("After clearing: " + fruits);
    }
}
