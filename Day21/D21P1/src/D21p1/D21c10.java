package D21p1;

import java.util.*;

class Room {
    int number;
    String type;
    double price;
    boolean isBooked;

    Room(int number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    @Override
    public String toString() {
        return "Room " + number + " (" + type + "), Price: Rs." + price + ", Status: " 
               + (isBooked ? "Booked" : "Available");
    }
}

public class D21c10{
    private static Hashtable<Integer, Room> rooms = new Hashtable<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Preload some rooms
        rooms.put(101, new Room(101, "Single", 1500));
        rooms.put(102, new Room(102, "Double", 2500));
        rooms.put(103, new Room(103, "Suite", 4000));
        rooms.put(104, new Room(104, "Deluxe", 3000));

        while (true) {
            System.out.println("\n--- Hotel Room System ---");
            System.out.println("1. View All Rooms");
            System.out.println("2. Check Availability");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: viewAllRooms(); break;
                case 2: checkAvailability(); break;
                case 3: bookRoom(); break;
                case 4: cancelBooking(); break;
                case 5: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // View all rooms
    private static void viewAllRooms() {
        System.out.println("\n--- Room List ---");
        for (Room r : rooms.values()) {
            System.out.println(r);
        }
    }

    // Check room availability
    private static void checkAvailability() {
        System.out.print("Enter Room Number: ");
        int num = sc.nextInt();
        Room r = rooms.get(num);
        if (r == null) {
            System.out.println("Room does not exist!");
            return;
        }
        System.out.println(r.isBooked ? "❌ Room already booked!" : "✅ Room is available!");
    }

    // Book a room
    private static void bookRoom() {
        System.out.print("Enter Room Number to Book: ");
        int num = sc.nextInt();
        Room r = rooms.get(num);
        if (r == null) {
            System.out.println("Room does not exist!");
            return;
        }
        if (r.isBooked) {
            System.out.println("❌ Room already booked!");
        } else {
            r.isBooked = true;
            System.out.println("✅ Room " + num + " booked successfully!");
        }
    }

    // Cancel booking
    private static void cancelBooking() {
        System.out.print("Enter Room Number to Cancel: ");
        int num = sc.nextInt();
        Room r = rooms.get(num);
        if (r == null) {
            System.out.println("Room does not exist!");
            return;
        }
        if (!r.isBooked) {
            System.out.println("Room is not booked yet!");
        } else {
            r.isBooked = false;
            System.out.println("✅ Booking cancelled for Room " + num);
        }
    }
}
