package D22p1;

import java.util.PriorityQueue;

public class D22j13 {
    public static void main(String[] args) {
        // Creating a PriorityQueue of integers
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Adding elements
        pq.add(40);
        pq.add(10);
        pq.add(30);
        pq.add(20);

        System.out.println("PriorityQueue: " + pq);

        // Accessing the head element (smallest in natural order)
        System.out.println("Head element (peek): " + pq.peek());

        // Removing elements (always removes smallest element first)
        System.out.println("Removed: " + pq.poll());
        System.out.println("PriorityQueue after poll: " + pq);

        // Adding more elements
        pq.add(5);
        pq.add(50);
        System.out.println("Updated PriorityQueue: " + pq);

        // Iterating over elements (not guaranteed sorted order)
        System.out.println("Iterating over queue:");
        for (int num : pq) {
            System.out.println(num);
        }

        // Removing all elements
        while (!pq.isEmpty()) {
            System.out.println("Removed: " + pq.poll());
        }
    }
}
