
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // ========== One-to-One (Unidirectional) ==========
    @OneToOne
    @JoinColumn(name = "profile_id")  // foreign key in employee table
    private EmployeeProfile profile;

    // ========== Many-to-One (Bidirectional with Department) ==========
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // ========== Many-to-Many (Bidirectional) ==========
    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects = new HashSet<>();
}
