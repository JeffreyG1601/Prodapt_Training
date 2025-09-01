package D27p1;

import java.util.function.*;
import java.util.*;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class D27c2 {
    public static void main(String[] args) {
        // Create a list of students
        List<Student> students = Arrays.asList(
                new Student("Alice", 85),
                new Student("Bob", 40),
                new Student("Charlie", 72),
                new Student("David", 30)
        );

        // 1. Predicate<T> -> Check if a student has passed
        Predicate<Student> hasPassed = (s) -> s.marks >= 50;

        // 2. Consumer<T> -> Print student details
        Consumer<Student> printStudent = (s) -> 
            System.out.println(s.name + " scored " + s.marks + " marks.");

        // 3. Supplier<T> -> Supply a random bonus mark between 1–10
        Supplier<Integer> bonusSupplier = () -> new Random().nextInt(10) + 1;

        // 4. Function<T,R> -> Convert student name into its length
        Function<Student, Integer> nameLength = (s) -> s.name.length();

        // ----------- Using all 4 interfaces together -------------
        for (Student s : students) {
            // Print details (Consumer)
            printStudent.accept(s);

            // Check pass/fail (Predicate)
            if (hasPassed.test(s)) {
                System.out.println("✅ " + s.name + " has PASSED.");
            } else {
                System.out.println("❌ " + s.name + " has FAILED.");
            }

            // Add bonus marks (Supplier)
            int bonus = bonusSupplier.get();
            System.out.println("🎁 Bonus Marks: " + bonus + " (new total = " + (s.marks + bonus) + ")");

            // Get length of name (Function)
            System.out.println("📏 Length of name '" + s.name + "' = " + nameLength.apply(s));
            
            System.out.println("--------------------------------------------------");
        }
    }
}

