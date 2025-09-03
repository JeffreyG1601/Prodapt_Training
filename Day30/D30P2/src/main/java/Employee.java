import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"detail", "department", "projects"})
@Entity
@Table(name = "hibernateemployee")
public class Employee {
    @Id
    private int id;

    @Column(name = "ename")
    private String name;

    private int age;
    private int salary;
    private String designation;

    // One-to-One
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private EmployeeDetail detail;

    // Many-to-One
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id")
    private Department department;

    // Many-to-Many
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "employee_project",
        joinColumns = @JoinColumn(name = "id"),          // must match hibernateemployee PK
        inverseJoinColumns = @JoinColumn(name = "pid")   // project PK
    )
    private Set<Project> projects = new HashSet<>();
}
