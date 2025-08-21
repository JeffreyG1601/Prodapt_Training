package D21p1;

import java.util.*;

class Student1 {
    int rollNo;
    String name;

    Student1(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public String toString() {
        return rollNo + " - " + name;
    }
}

public class D21c15 {
    private static List<Student1> students = new ArrayList<>();
    private static HashMap<String, HashMap<Integer, Boolean>> attendanceRecords = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Predefined students
        students.add(new Student1(1, "Arun"));
        students.add(new Student1(2, "Priya"));
        students.add(new Student1(3, "Rahul"));
        students.add(new Student1(4, "Sneha"));
        students.add(new Student1(5, "Vikram"));

        while (true) {
            System.out.println("\n=== School Attendance System ===");
            System.out.println("1. Mark Attendance");
            System.out.println("2. Check Student Attendance on a Date");
            System.out.println("3. View All Attendance for a Date");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = sc.next();
                    // If date not present, create fresh attendance record with all Absent
                    attendanceRecords.putIfAbsent(date, new HashMap<>());
                    for (Student1 s : students) {
                        attendanceRecords.get(date).putIfAbsent(s.rollNo, false);
                    }

                    System.out.println("Enter Roll No to mark Present (or 0 to stop):");
                    while (true) {
                        int roll = sc.nextInt();
                        if (roll == 0) break;
                        if (attendanceRecords.get(date).containsKey(roll)) {
                            attendanceRecords.get(date).put(roll, true);
                            System.out.println("✅ Marked Present: " + getStudentName(roll));
                        } else {
                            System.out.println("⚠️ Student not found.");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String checkDate = sc.next();
                    if (!attendanceRecords.containsKey(checkDate)) {
                        System.out.println("⚠️ No attendance recorded for this date.");
                        break;
                    }
                    System.out.print("Enter Roll No: ");
                    int rollCheck = sc.nextInt();
                    if (attendanceRecords.get(checkDate).containsKey(rollCheck)) {
                        boolean status = attendanceRecords.get(checkDate).get(rollCheck);
                        System.out.println(getStudentName(rollCheck) + " was " + (status ? "✅ Present" : "❌ Absent") + " on " + checkDate);
                    } else {
                        System.out.println("⚠️ Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String viewDate = sc.next();
                    if (!attendanceRecords.containsKey(viewDate)) {
                        System.out.println("⚠️ No attendance recorded for this date.");
                        break;
                    }
                    System.out.println("📋 Attendance on " + viewDate + ":");
                    for (Student1 s : students) {
                        boolean status = attendanceRecords.get(viewDate).get(s.rollNo);
                        System.out.println(s + " → " + (status ? "Present" : "Absent"));
                    }
                    break;

                case 4:
                    System.out.println("👋 Exiting Attendance System.");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static String getStudentName(int rollNo) {
        for (Student1 s : students) {
            if (s.rollNo == rollNo) return s.name;
        }
        return "Unknown";
    }
}
