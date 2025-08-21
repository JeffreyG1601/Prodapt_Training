package D21p1;

import java.util.*;

class Patient {
    int id;
    String name;
    int age;
    String condition;
    int criticalLevel; // Higher value = more critical

    Patient(int id, String name, int age, String condition, int criticalLevel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.criticalLevel = criticalLevel;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age +
               ", Condition: " + condition + ", Critical Level: " + criticalLevel;
    }
}

public class D21c6 {
    private static PriorityQueue<Patient> patients =
            new PriorityQueue<>((p1, p2) -> Integer.compare(p2.criticalLevel, p1.criticalLevel)); // High → Low
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hospital System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. View Most Critical Patient");
            System.out.println("4. Treat Most Critical Patient (Remove)");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addPatient(); break;
                case 2: viewAllPatients(); break;
                case 3: viewCriticalPatient(); break;
                case 4: treatCriticalPatient(); break;
                case 5: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Add patient
    private static void addPatient() {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Condition: ");
        String condition = sc.nextLine();
        System.out.print("Enter Critical Level (1-10): ");
        int level = sc.nextInt();

        patients.add(new Patient(id, name, age, condition, level));
        System.out.println("Patient added successfully!");
    }

    // View all patients
    private static void viewAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients recorded!");
            return;
        }
        System.out.println("Patient Records:");
        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    // View most critical patient
    private static void viewCriticalPatient() {
        if (patients.isEmpty()) {
            System.out.println("No patients!");
            return;
        }
        System.out.println("Most Critical Patient: " + patients.peek());
    }

    // Treat and remove most critical patient
    private static void treatCriticalPatient() {
        if (patients.isEmpty()) {
            System.out.println("No patients to treat!");
            return;
        }
        Patient treated = patients.poll();
        System.out.println("Treated & removed patient: " + treated);
    }
}
