package D21p1;

import java.util.*;

class Patient {
    int id;
    String name;
    int age;
    String condition;
    int criticalLevel; // Higher = more critical
    Doctor doctor;     // Assigned doctor

    Patient(int id, String name, int age, String condition, int criticalLevel, Doctor doctor) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.criticalLevel = criticalLevel;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age +
               ", Condition: " + condition + ", Critical Level: " + criticalLevel +
               ", Doctor: " + (doctor != null ? doctor.name : "Unassigned");
    }
}

class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialization: " + specialization;
    }
}

public class D21c6 {
    private static PriorityQueue<Patient> patients = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.criticalLevel, p1.criticalLevel)); // High → Low
    private static Map<Integer, Doctor> doctors = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hospital System ---");
            System.out.println("1. Add Doctor");g
            System.out.println("2. View All Doctors");
            System.out.println("3. Add Patient");
            System.out.println("4. View All Patients");
            System.out.println("5. View Most Critical Patient");
            System.out.println("6. Treat Most Critical Patient (Remove)");
            System.out.println("7. View Patients by Doctor");
            System.out.println("8. Count Patients per Doctor");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addDoctor(); break;
                case 2: viewAllDoctors(); break;
                case 3: addPatient(); break;
                case 4: viewAllPatients(); break;
                case 5: viewCriticalPatient(); break;
                case 6: treatCriticalPatient(); break;
                case 7: viewPatientsByDoctor(); break;
                case 8: countPatientsPerDoctor(); break;
                case 9: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Add doctor
    private static void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();

        doctors.put(id, new Doctor(id, name, specialization));
        System.out.println("Doctor added successfully!");
    }

    // View all doctors
    private static void viewAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available!");
            return;
        }
        System.out.println("Doctor Records:");
        for (Doctor d : doctors.values()) {
            System.out.println(d);
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
        System.out.print("Enter Doctor ID (or -1 if none): ");
        int docId = sc.nextInt();

        Doctor doctor = doctors.getOrDefault(docId, null);
        patients.add(new Patient(id, name, age, condition, level, doctor));
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

    // View patients assigned to a doctor
    private static void viewPatientsByDoctor() {
        System.out.print("Enter Doctor ID: ");
        int docId = sc.nextInt();
        Doctor doc = doctors.get(docId);
        if (doc == null) {
            System.out.println("Doctor not found!");
            return;
        }
        System.out.println("Patients under Dr. " + doc.name + ":");
        for (Patient p : patients) {
            if (p.doctor != null && p.doctor.id == docId) {
                System.out.println(p);
            }
        }
    }

    // Count patients per doctor
    private static void countPatientsPerDoctor() {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Patient p : patients) {
            if (p.doctor != null) {
                countMap.put(p.doctor.id, countMap.getOrDefault(p.doctor.id, 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            Doctor doc = doctors.get(entry.getKey());
            System.out.println(doc.name + " (" + doc.specialization + ") has " + entry.getValue() + " patients.");
        }
    }
}
