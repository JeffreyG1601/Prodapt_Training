package D22p1;

import java.util.LinkedList;
import java.util.Queue;

public class D22j12 {
    public static void main(String[] args) {
        // Creating a Queue using LinkedList
        Queue<String> queue = new LinkedList<>();

        // Adding elements to the queue (Enqueue)
        queue.add("Apple");
        queue.add("Banana");
        queue.add("Mango");
        queue.add("Orange");

        System.out.println("Queue: " + queue);

        // Accessing the front element without removing it
        System.out.println("Front element (peek): " + queue.peek());

        // Removing elements from the queue (Dequeue)
        System.out.println("Removed: " + queue.remove());
        System.out.println("Queue after remove: " + queue);

        // Using poll (removes element or returns null if empty)
        System.out.println("Removed using poll: " + queue.poll());
        System.out.println("Queue after poll: " + queue);

        // Checking size
        System.out.println("Queue size: " + queue.size());

        // Checking if queue is empty
        System.out.println("Is queue empty? " + queue.isEmpty());

        // Iterating over queue
        System.out.println("Iterating over queue:");
        for (String item : queue) {
            System.out.println(item);
        }

        // Clearing the queue
        queue.clear();
        System.out.println("After clearing: " + queue);
    }
}
