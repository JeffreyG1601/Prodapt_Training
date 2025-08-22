package D22p1;

import java.util.TreeMap;

public class D22j10 {
    public static void main(String[] args) {
        // Creating a TreeMap (Key = String, Value = Integer)
        TreeMap<String, Integer> fruits = new TreeMap<>();

        // Adding key-value pairs
        fruits.put("Apple", 10);
        fruits.put("Banana", 20);
        fruits.put("Mango", 15);
        fruits.put("Orange", 25);
        fruits.put("Banana", 30); // Duplicate key → overwrites value

        System.out.println("Fruits map (sorted by keys): " + fruits);

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

        // First and last keys
        System.out.println("First key: " + fruits.firstKey());
        System.out.println("Last key: " + fruits.lastKey());

        // Iterating through key-value pairs (always sorted by keys)
        System.out.println("Entries (sorted by keys):");
        for (String key : fruits.keySet()) {
            System.out.println(key + " -> " + fruits.get(key));
        }

        // Clearing the map
        fruits.clear();
        System.out.println("After clearing: " + fruits);
    }
}
