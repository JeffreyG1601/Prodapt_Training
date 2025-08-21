package D21p1;

import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isAvailable;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable;
    }

    // HashSet needs equals & hashCode (based on id)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book b = (Book) obj;
        return this.id == b.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class D21c4 {
    private static HashSet<Book> library = new HashSet<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Library System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Check Availability");
            System.out.println("3. Lend Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display All Books");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addBook(); break;
                case 2: checkAvailability(); break;
                case 3: lendBook(); break;
                case 4: returnBook(); break;
                case 5: displayBooks(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Add new book
    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book newBook = new Book(id, "", "");
        if (library.contains(newBook)) {
            System.out.println("Book with this ID already exists!");
            return;
        }
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        library.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    // Check if book is available
    private static void checkAvailability() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        for (Book b : library) {
            if (b.id == id) {
                System.out.println("Book Found: " + b);
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Lend book
    private static void lendBook() {
        System.out.print("Enter Book ID to lend: ");
        int id = sc.nextInt();
        for (Book b : library) {
            if (b.id == id) {
                if (b.isAvailable) {
                    b.isAvailable = false;
                    System.out.println("Book lent successfully!");
                } else {
                    System.out.println("Book is already lent out!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Return book
    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        for (Book b : library) {
            if (b.id == id) {
                if (!b.isAvailable) {
                    b.isAvailable = true;
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book was not lent out!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Display all books
    private static void displayBooks() {
        if (library.isEmpty()) {
            System.out.println("No books in library!");
            return;
        }
        System.out.println("Library Books:");
        for (Book b : library) {
            System.out.println(b);
        }
    }
}
