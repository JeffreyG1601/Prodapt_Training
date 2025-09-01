package D27p2;
import java.util.*;
import java.util.stream.Collectors;
class Employee {

    private int id;
    private String name;
    private int age;
    private double salary;
    private String designation;

    // Constructor
    public Employee(int id, String name, int age, double salary, String designation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public String getDesignation() { return designation; }

    // toString for display
    @Override
    public String toString() {
        return id + " - " + name + " - " + age + " - " + salary + " - " + designation;
    }
}

public class D27j4 {
	 public static void main(String[] args) {
	        // Create Employee List
	        List<Employee> employees = Arrays.asList(
	            new Employee(101, "Ravi", 28, 50000, "Developer"),
	            new Employee(102, "Meena", 32, 65000, "Manager"),
	            new Employee(103, "Arun", 25, 40000, "Tester"),
	            new Employee(104, "Priya", 29, 55000, "Developer"),
	            new Employee(105, "Kiran", 35, 70000, "Architect")
	        );

	        System.out.println("---- All Employee Records ----");
	       employees.stream().forEach(System.out::println);

	        System.out.println("---- DEVELOPERS----");   
	        employees.stream()
	        .filter(emp -> emp.getDesignation().equals("Developer"))
	        .forEach(System.out::println);
	        
	        System.out.println("==============");
	        employees.stream()
	        .filter(emp -> emp.getSalary() > 50000)
	        .forEach(System.out::println);
	        
	        System.out.println("= Higest paid Salary ");
	        Employee highestPaid = employees.stream()
	                .max(Comparator.comparingDouble(Employee::getSalary))
	                .orElse(null);
	        System.out.println("Highest Paid: " + highestPaid);

	        System.out.println("================= Youngest Employee==");Employee youngest = employees.stream()
	                .min(Comparator.comparingInt(Employee::getAge))
	                .orElse(null);

	        System.out.println("Youngest Employee: " + youngest);
	        
	        System.out.println("=========== SORT------");
	        employees.stream()
	        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
	        .forEach(System.out::println);
	        
	        System.out.println("========GET NAMES ");
	   
	        System.out.println("============== All names ");
	        List<String> names = employees.stream()
	                .map(Employee::getName)
	                .collect(Collectors.toList());

	        System.out.println("Employee Names: " + names);
	        System.out.println("===========Avg Salary ===============");
	        
	        double avgAge = employees.stream()
	                .mapToInt(Employee::getAge)
	                .average()
	                .orElse(0.0);

	        System.out.println("Average Age: " + avgAge);
	        
	        System.out.println("===============");
	        System.out.println("Top 3 highest paid employees:");
	        employees.stream()
	                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
	                .limit(3)
	                .forEach(System.out::println);
	        System.out.println("============================");
	        System.out.println("Count of employees by designation:");
	        Map<String, Long> countByDesignation = employees.stream()
	                .collect(Collectors.groupingBy(Employee::getDesignation, Collectors.counting()));

	        System.out.println(countByDesignation);
	        
	    }
}
