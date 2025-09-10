package com;

import java.util.*;



public class D34j4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Student> map = new HashMap<>();

        map.put(1, new Student(1, "Arjun", "10A", 15, 80, 75, 90));
        map.put(2, new Student(2, "Meera", "10B", 16, 85, 88, 92));

        System.out.print("How many students you want to enter: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Id: "); int id = sc.nextInt();
            System.out.print("Enter Name: "); String name = sc.next();
            System.out.print("Enter Class: "); String cls = sc.next();
            System.out.print("Enter Age: "); int age = sc.nextInt();
            System.out.print("Enter Mark1: "); int m1 = sc.nextInt();
            System.out.print("Enter Mark2: "); int m2 = sc.nextInt();
            System.out.print("Enter Mark3: "); int m3 = sc.nextInt();
            map.put(id, new Student(id, name, cls, age, m1, m2, m3));
        }

        while (true) {
            System.out.println("\n1)Display all details");
            System.out.println("2)Display Top Mark");
            System.out.println("3)Update Mark");
            System.out.println("4)Add student");
            System.out.println("5)Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    for (Student s : map.values()) System.out.println(s);
                    break;
                case 2:
                    Student top = null;
                    for (Student s : map.values()) {
                        if (top == null || s.aggregate > top.aggregate) top = s;
                    }
                    System.out.println("Top Student: " + top);
                    break;
                case 3:
                    System.out.print("Enter Id to update marks: ");
                    int uid = sc.nextInt();
                    if (map.containsKey(uid)) {
                        System.out.print("Enter new Mark1: "); int m1 = sc.nextInt();
                        System.out.print("Enter new Mark2: "); int m2 = sc.nextInt();
                        System.out.print("Enter new Mark3: "); int m3 = sc.nextInt();
                        map.get(uid).updateMarks(m1, m2, m3);
                        System.out.println("Marks updated");
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                case 4:
                    System.out.print("Enter Id: "); int id = sc.nextInt();
                    System.out.print("Enter Name: "); String name = sc.next();
                    System.out.print("Enter Class: "); String cls = sc.next();
                    System.out.print("Enter Age: "); int age = sc.nextInt();
                    System.out.print("Enter Mark1: "); int m1 = sc.nextInt();
                    System.out.print("Enter Mark2: "); int m2 = sc.nextInt();
                    System.out.print("Enter Mark3: "); int m3 = sc.nextInt();
                    map.put(id, new Student(id, name, cls, age, m1, m2, m3));
                    System.out.println("Student added");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
