package D22p1;

import java.util.ArrayDeque;
import java.util.Deque;

public class D22j14 {
    public static void main(String[] args) {
        // Creating a Deque using ArrayDeque
        Deque<String> deque = new ArrayDeque<>();

        // Adding elements at the end (like Queue)
        deque.add("Apple");
        deque.add("Banana");
        deque.add("Mango");
        System.out.println("Deque: " + deque);

        // Adding elements at the front
        deque.addFirst("Orange");
        deque.addFirst("Grapes");
        System.out.println("After adding at front: " + deque);

        // Adding elements at the rear
        deque.addLast("Pineapple");
        System.out.println("After adding at rear: " + deque);

        // Accessing first and last elements
        System.out.println("First element: " + deque.getFirst());
        System.out.println("Last element: " + deque.getLast());

        // Removing elements from both ends
        System.out.println("Removed from front: " + deque.removeFirst());
        System.out.println("Removed from rear: " + deque.removeLast());
        System.out.println("Deque after removals: " + deque);

        // Iterating over elements
        System.out.println("Iterating over deque:");
        for (String fruit : deque) {
            System.out.println(fruit);
        }

        // Clearing deque
        deque.clear();
        System.out.println("After clearing: " + deque);
    }
}
