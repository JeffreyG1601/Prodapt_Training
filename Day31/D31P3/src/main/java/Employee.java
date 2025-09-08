import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "emp1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"departmentBi", "departmentBiMany", "departmentUniMany", "departments"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empName;

    // 1:1 Bidirectional (Employee ↔ Department)
    @OneToOne
    @JoinColumn(name = "dep_bi_id")
    private Department departmentBi;

    // M:1 Bidirectional (Employee -> Department)
    @ManyToOne
    @JoinColumn(name = "dep_bi_many_id")
    private Department departmentBiMany;

    // M:1 Unidirectional (Employee -> Department)
    @ManyToOne
    @JoinColumn(name = "dep_uni_many_id")
    private Department departmentUniMany;

    // M:M (Employees can belong to multiple Departments)
    @ManyToMany
    @JoinTable(
            name = "emp_dep_map",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "dep_id")
    )
    @Builder.Default
    private Set<Department> departments = new HashSet<>();

    // equals & hashCode only on ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee e = (Employee) o;
        return id != null && id.equals(e.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
