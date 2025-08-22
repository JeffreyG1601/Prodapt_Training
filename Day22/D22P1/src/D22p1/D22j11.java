package D22p1;

import java.util.Hashtable;

public class D22j11 {
    public static void main(String[] args) {
        // Creating a Hashtable (Key = String, Value = Integer)
        Hashtable<String, Integer> fruits = new Hashtable<>();

        // Adding key-value pairs
        fruits.put("Apple", 10);
        fruits.put("Banana", 20);
        fruits.put("Mango", 15);
        fruits.put("Orange", 25);
        fruits.put("Banana", 30); // Duplicate key → overwrites value

        System.out.println("Fruits map: " + fruits);

        // Accessing values by key
        System.out.println("Price of Mango: " + fruits.get("Mango"));

        // Removing an entry
        fruits.remove("Apple");
        System.out.println("After removing Apple: " + fruits);

        // Checking size
        System.out.println("Number of entries: " + fruits.size());

        // Checking if map contains key or value
        System.out.println("Contains key 'Orange'? " + fruits.containsKey("Orange"));
        System.out.println("Contains value 25? " + fruits.containsValue(25));

        // Iterating through key-value pairs (unordered)
        System.out.println("Entries:");
        for (String key : fruits.keySet()) {
            System.out.println(key + " -> " + fruits.get(key));
        }

        // Clearing the map
        fruits.clear();
        System.out.println("After clearing: " + fruits);
    }
}
