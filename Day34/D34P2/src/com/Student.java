package com;
public class Student {
    public int id;
	int age;
    public String name;
	String cls;
    int mark1, mark2, mark3;
    public double aggregate;

    public Student(int id, String name, String cls, int age, int m1, int m2, int m3) {
        this.id = id;
        this.name = name;
        this.cls = cls;
        this.age = age;
        this.mark1 = m1;
        this.mark2 = m2;
        this.mark3 = m3;
        this.aggregate = (m1 + m2 + m3) / 3.0;
    }

    public void updateMarks(int m1, int m2, int m3) {
        this.mark1 = m1;
        this.mark2 = m2;
        this.mark3 = m3;
        this.aggregate = (m1 + m2 + m3) / 3.0;
    }

    public String toString() {
        return id + " " + name + " " + cls + " " + age + " " + mark1 + " " + mark2 + " " + mark3 + " " + aggregate;
    }
}