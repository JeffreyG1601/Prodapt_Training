public class Employee {
    private int id;
    private String name;
    private int age;
    private int salary;
    private String designation;

    public Employee(int id, String name, int age, int salary, String designation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getSalary() { return salary; }
    public String getDesignation() { return designation; }

    public void setSalary(int salary) { this.salary = salary; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + age + " | " + salary + " | " + designation;
    }
}
