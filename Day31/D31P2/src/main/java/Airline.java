
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"flightsUni", "flightsBi"})
@ToString(exclude = {"flightsUni", "flightsBi"})
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airlineName;

    @OneToMany
    @JoinColumn(name = "airline_fk")
    @Builder.Default
    private Set<Flight> flightsUni = new HashSet<>();

    @OneToMany(mappedBy = "airlineBi")
    @Builder.Default
    private Set<Flight> flightsBi = new HashSet<>();
}
