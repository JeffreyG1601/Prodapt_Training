import javax.persistence.*;

@Entity
public class ParkingSpace {
    @Id @GeneratedValue
    private Long id;
    private int lotNumber;
    
    // Constructors, Getters, Setters...
    public ParkingSpace() {}
    public ParkingSpace(int lotNumber) { this.lotNumber = lotNumber; }
    @Override public String toString() { return "ParkingSpace{id=" + id + ", lotNumber=" + lotNumber + "}"; }
}