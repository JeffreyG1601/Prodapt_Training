package D22p1;

import java.util.LinkedList;

public class D22j2 {
    public static void main(String[] args) {
        // Creating a LinkedList of Strings
        LinkedList<String> fruits = new LinkedList<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Orange");

        System.out.println("Fruits: " + fruits);

        // Adding at specific positions
        fruits.addFirst("Pineapple");
        fruits.addLast("Watermelon");
        System.out.println("After addFirst & addLast: " + fruits);

        // Accessing elements
        System.out.println("First fruit: " + fruits.getFirst());
        System.out.println("Last fruit: " + fruits.getLast());

        // Modifying an element
        fruits.set(2, "Grapes");  // replaces Mango with Grapes
        System.out.println("After modification: " + fruits);

        // Removing elements
        fruits.remove("Banana");   // Removes by value
        fruits.remove(0);          // Removes by index
        fruits.removeFirst();      // Removes first element
        fruits.removeLast();       // Removes last element
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

