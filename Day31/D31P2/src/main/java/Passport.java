
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;
    private String nationality;

    // One-to-One (Bi)
    @OneToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passengerBi;
}
