
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"airlineUni", "airlineBi", "bookings", "passengers"})
@ToString(exclude = {"airlineUni", "airlineBi", "bookings", "passengers"})
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "airline_fk")
    private Airline airlineUni;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airlineBi;

    @OneToMany(mappedBy = "flightUni")
    @Builder.Default
    private Set<Booking> bookings = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "flight_passenger",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    @Builder.Default
    private Set<Passenger> passengers = new HashSet<>();
}
