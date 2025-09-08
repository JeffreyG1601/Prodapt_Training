
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dep1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"employeesBi", "employeesUni", "employeeOneToOneBi", "employeeOneToOneUni"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deptName;

    // 1:1 Bidirectional (Department ↔ Employee)
    @OneToOne(mappedBy = "departmentBi")
    private Employee employeeOneToOneBi;

    // 1:1 Unidirectional (Department -> Employee)
    @OneToOne
    @JoinColumn(name = "emp_uni_id")
    private Employee employeeOneToOneUni;

    // 1:M Bidirectional
    @OneToMany(mappedBy = "departmentBiMany")
    @Builder.Default
    private Set<Employee> employeesBi = new HashSet<>();

    // 1:M Unidirectional
    @OneToMany
    @JoinColumn(name = "dep_uni_id")
    @Builder.Default
    private Set<Employee> employeesUni = new HashSet<>();

    // equals & hashCode only on ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department d = (Department) o;
        return id != null && id.equals(d.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
