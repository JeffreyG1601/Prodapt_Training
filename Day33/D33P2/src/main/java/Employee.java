import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int salary;

    @ManyToOne
    private Department department;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ParkingSpace parkingSpace;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Project> projects = new HashSet<>();

    // Constructors, Getters, Setters...
    public Employee() {}
    public Employee(String name, int salary) { this.name = name; this.salary = salary; }
    public String getName() { return name; }
    public void setDepartment(Department department) { this.department = department; }
    public void setParkingSpace(ParkingSpace parkingSpace) { this.parkingSpace = parkingSpace; }
    public void addProject(Project proj) { this.projects.add(proj); }
    @Override public String toString() { return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}"; }
}