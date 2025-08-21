package D21p1;

import java.util.*;

class Student {
    int id;
    String name;
    int age;

    Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}

public class D21C1 {
    private static Map<Integer, Student> students = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: searchStudent(); break;
                case 3: updateStudent(); break;
                case 4: displayAll(); break;
                case 5: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Add a new student
    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        if (students.containsKey(id)) {
            System.out.println("Student with this ID already exists!");
            return;
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        Student s = new Student(id, name, age);
        students.put(id, s);
        System.out.println("Student added successfully!");
    }

    // Search student by ID
    private static void searchStudent() {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();
        Student s = students.get(id);
        if (s != null) {
            System.out.println("Found: " + s);
        } else {
            System.out.println("Student not found!");
        }
    }

    // Update student record
    private static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Student s = students.get(id);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.print("Enter new name (leave blank to keep same): ");
        String name = sc.nextLine();
        if (!name.trim().isEmpty()) {
            s.name = name;
        }
        System.out.print("Enter new age (or -1 to keep same): ");
        int age = sc.nextInt();
        if (age != -1) {
            s.age = age;
        }
        System.out.println("Student updated successfully!");
    }

    // Display all students
    private static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No records found!");
            return;
        }
        System.out.println("All Students:");
        for (Student s : students.values()) {
            System.out.println(s);
        }
    }
}
