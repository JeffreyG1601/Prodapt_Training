package D21p1;

import java.util.*;

class Passenger {
    int id;
    String name;
    String seatNo;

    Passenger(int id, String name, String seatNo) {
        this.id = id;
        this.name = name;
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return "Passenger ID: " + id + ", Name: " + name + ", Seat: " + seatNo;
    }
}

public class D21c9 {
    private static Vector<Passenger> passengers = new Vector<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Flight Booking System ---");
            System.out.println("1. Add Passenger");
            System.out.println("2. View Passenger List");
            System.out.println("3. Search Passenger");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addPassenger(); break;
                case 2: viewPassengers(); break;
                case 3: searchPassenger(); break;
                case 4: cancelBooking(); break;
                case 5: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Add passenger
    private static void addPassenger() {
        System.out.print("Enter Passenger ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        // Prevent duplicate ID
        for (Passenger p : passengers) {
            if (p.id == id) {
                System.out.println("Passenger with this ID already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Seat No: ");
        String seat = sc.nextLine();

        // Prevent duplicate seat assignment
        for (Passenger p : passengers) {
            if (p.seatNo.equalsIgnoreCase(seat)) {
                System.out.println("Seat already booked!");
                return;
            }
        }

        passengers.add(new Passenger(id, name, seat));
        System.out.println("✅ Passenger added successfully!");
    }

    // View all passengers
    private static void viewPassengers() {
        if (passengers.isEmpty()) {
            System.out.println("No passengers booked yet!");
            return;
        }
        System.out.println("\n--- Passenger List ---");
        for (Passenger p : passengers) {
            System.out.println(p);
        }
    }

    // Search passenger by ID
    private static void searchPassenger() {
        System.out.print("Enter Passenger ID to search: ");
        int id = sc.nextInt();
        for (Passenger p : passengers) {
            if (p.id == id) {
                System.out.println("Found: " + p);
                return;
            }
        }
        System.out.println("Passenger not found!");
    }

    // Cancel booking
    private static void cancelBooking() {
        System.out.print("Enter Passenger ID to cancel: ");
        int id = sc.nextInt();
        Iterator<Passenger> it = passengers.iterator();
        while (it.hasNext()) {
            Passenger p = it.next();
            if (p.id == id) {
                it.remove();
                System.out.println("❌ Booking cancelled for: " + p.name);
                return;
            }
        }
        System.out.println("Passenger not found!");
    }
}
