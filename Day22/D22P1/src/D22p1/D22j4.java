package D22p1;

import java.util.Stack;

public class D22j4 {
    public static void main(String[] args) {
        // Creating a Stack of Strings
        Stack<String> fruits = new Stack<>();

        // Pushing elements (adds to the top of stack)
        fruits.push("Apple");
        fruits.push("Banana");
        fruits.push("Mango");
        fruits.push("Orange");

        System.out.println("Stack: " + fruits);

        // Peeking (view top element without removing)
        System.out.println("Top element: " + fruits.peek());

        // Popping elements (removes from top)
        System.out.println("Popped: " + fruits.pop());
        System.out.println("After pop: " + fruits);

        // Searching (1-based index from top)
        System.out.println("Position of Banana: " + fruits.search("Banana"));

        // Checking if empty
        System.out.println("Is stack empty? " + fruits.isEmpty());

        // Iterating through stack
        System.out.println("Stack elements:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
