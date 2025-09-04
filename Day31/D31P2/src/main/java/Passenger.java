import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"passportUni", "passportBi", "bookingsUni", "bookingsBi", "flights"})
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportId;
    private String name;
    private int age;

    @OneToOne
    @JoinColumn(name = "passport_ref")
    private Passport passportUni;

    @OneToOne(mappedBy = "passengerBi")
    private Passport passportBi;

    @OneToMany
    @JoinColumn(name = "passenger_id")
    @Builder.Default
    private Set<Booking> bookingsUni = new HashSet<>();

    @OneToMany(mappedBy = "passengerBi")
    @Builder.Default
    private Set<Booking> bookingsBi = new HashSet<>();

    @ManyToMany(mappedBy = "passengers")
    @Builder.Default
    private Set<Flight> flights = new HashSet<>();

    // equals & hashCode ONLY on id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger that = (Passenger) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
