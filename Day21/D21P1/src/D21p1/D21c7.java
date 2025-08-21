package D21p1;

import java.util.*;

class Movie {
    String title;
    Map<String, TreeSet<Integer>> showSeats; // time → seats booked

    Movie(String title, List<String> timings) {
        this.title = title;
        this.showSeats = new HashMap<>();
        for (String t : timings) {
            showSeats.put(t, new TreeSet<>()); // no seats booked initially
        }
    }

    @Override
    public String toString() {
        return "Movie: " + title + ", Timings: " + showSeats.keySet();
    }
}

public class D21c7 {
    private static List<Movie> movies = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-existing movie data
        movies.add(new Movie("Avengers: Endgame", Arrays.asList("10:00 AM", "2:00 PM", "6:00 PM")));
        movies.add(new Movie("Inception", Arrays.asList("11:00 AM", "3:00 PM", "7:00 PM")));
        movies.add(new Movie("Interstellar", Arrays.asList("12:00 PM", "4:00 PM", "8:00 PM")));

        while (true) {
            System.out.println("\n--- Cinema Ticket Booking ---");
            System.out.println("1. View Movies");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Booked Seats");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: viewMovies(); break;
                case 2: bookTicket(); break;
                case 3: viewBookedSeats(); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // View available movies
    private static void viewMovies() {
        System.out.println("\nAvailable Movies:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }
    }

    // Book ticket
    private static void bookTicket() {
        viewMovies();
        System.out.print("Select Movie (1-" + movies.size() + "): ");
        int movieIndex = sc.nextInt() - 1;

        if (movieIndex < 0 || movieIndex >= movies.size()) {
            System.out.println("Invalid movie selection!");
            return;
        }

        Movie selected = movies.get(movieIndex);
        System.out.println("Available timings: " + selected.showSeats.keySet());
        sc.nextLine();
        System.out.print("Enter Show Time: ");
        String time = sc.nextLine();

        if (!selected.showSeats.containsKey(time)) {
            System.out.println("Invalid time!");
            return;
        }

        System.out.print("Enter Seat Number (1-50): ");
        int seat = sc.nextInt();

        TreeSet<Integer> bookedSeats = selected.showSeats.get(time);

        if (seat < 1 || seat > 50) {
            System.out.println("Invalid seat number!");
            return;
        }
        if (bookedSeats.contains(seat)) {
            System.out.println("Seat already booked!");
            return;
        }

        bookedSeats.add(seat);
        System.out.println("✅ Ticket booked for " + selected.title + " at " + time + ", Seat: " + seat);
    }

    // View booked seats for a movie/time
    private static void viewBookedSeats() {
        viewMovies();
        System.out.print("Select Movie (1-" + movies.size() + "): ");
        int movieIndex = sc.nextInt() - 1;

        if (movieIndex < 0 || movieIndex >= movies.size()) {
            System.out.println("Invalid movie selection!");
            return;
        }

        Movie selected = movies.get(movieIndex);
        System.out.println("Available timings: " + selected.showSeats.keySet());
        sc.nextLine();
        System.out.print("Enter Show Time: ");
        String time = sc.nextLine();

        if (!selected.showSeats.containsKey(time)) {
            System.out.println("Invalid time!");
            return;
        }

        TreeSet<Integer> bookedSeats = selected.showSeats.get(time);
        if (bookedSeats.isEmpty()) {
            System.out.println("No seats booked yet!");
        } else {
            System.out.println("Booked Seats: " + bookedSeats);
        }
    }
}

