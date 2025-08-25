package D23p1;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
class Tap {
	LocalDateTime tstamp;
	String Station,line;
	double fare;
	public Tap(String datetime,String line,String Station) {
	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	this.tstamp=LocalDateTime.parse("2025-" +datetime,format);
	this.line=line;
	this.Station=Station;
	this.fare= 0;
	}
	
	public String toString() {
		return tstamp+"|"+line+"|"+Station+"|Fare:"+fare;
	}
}
interface FareRule{
	double apply(Tap current,Tap last, double curfare);
	boolean isEnabled();
}
class R1 implements FareRule{
	boolean enabled;
	double bfare;
	public R1(double bfare,boolean enabled) {
		this.bfare=bfare;
		this.enabled=enabled;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public double apply(Tap current,Tap last,double curfare) {
		if(!enabled) return curfare;
		return bfare;
	}
}
class R2 implements FareRule{
	boolean enabled;
	LocalTime mstart=LocalTime.of(8, 0);
	LocalTime mend=LocalTime.of(10, 0);
	LocalTime estart=LocalTime.of(18, 0);
	LocalTime eend=LocalTime.of(20, 0);
	double rate;
	public R2(double rate,boolean enabled) {
		this.rate=rate;
		this.enabled=enabled;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public double apply(Tap current,Tap last,double curfare) {
		if(!enabled||curfare == 0) return curfare;
		LocalTime t =current.tstamp.toLocalTime();
		if(t.isAfter(mstart.plusSeconds(1))&&t.isBefore(mend.plusSeconds(1))||t.isAfter(estart.plusSeconds(1))&&t.isBefore(eend.plusSeconds(1))) {return curfare*rate;}
		return curfare;
	}
}
class R3 implements FareRule{
	boolean enabled;
	int Timeelapsed;
	public R3(int Timeelapsed,boolean enabled) {
		this.Timeelapsed=Timeelapsed;
		this.enabled=enabled;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public double apply(Tap current,Tap last,double curfare) {
		if(!enabled) return curfare;
		if(last==null)return curfare;
		long time=java.time.Duration.between(last.tstamp, current.tstamp).toMinutes();
		if(time<=Timeelapsed) return 0;
		return curfare;
	}
}
class R4 implements FareRule{
	boolean enabled;
	LocalTime mstart=LocalTime.of(22, 0);
	LocalTime mend=LocalTime.of(23,59);
	double discount;
	public R4(double discount,boolean enabled) {
		this.discount=discount;
		this.enabled=enabled;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public double apply(Tap current,Tap last,double curfare) {
		if(!enabled||curfare==0) return curfare;
		LocalTime t=current.tstamp.toLocalTime();
		if(!t.isBefore(mstart)&&t.isBefore(mend)){
			return curfare*(1-discount/100.0);
		}
		return curfare;
	}
}
class R5 implements FareRule{
	boolean enabled;
	LocalTime mstart=LocalTime.of(0, 0);
	LocalTime mend=LocalTime.of(4,0);
	double discount;
	public R5(double discount,boolean enabled) {
		this.discount=discount;
		this.enabled=enabled;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public double apply(Tap current,Tap last,double curfare) {
		if(!enabled||curfare==0) return curfare;
		LocalTime t=current.tstamp.toLocalTime();
		if(!t.isBefore(mstart)&&t.isBefore(mend.plusSeconds(1))) {
			return curfare*(1-discount/100.0);
		}
		return curfare;
	}
}
class TariffEngine{
	List<FareRule> rules;
	public TariffEngine() {
		rules = new ArrayList<>();
	}
	public void addrule(FareRule rule) {
		rules.add(rule);
	}
	public double calculateFare(Tap current,Tap last) {
		double fare=0;
		for(FareRule rule :rules) {
			if(rule instanceof R1 &&rule.isEnabled() ) {
				fare=rule.apply(current, last, fare);
			}
		}
		for(FareRule rule :rules) {
			if(rule instanceof R2 &&rule.isEnabled() ) {
				fare=rule.apply(current, last, fare);
			}
		}
		for(FareRule rule :rules) {
			if(rule instanceof R3 &&rule.isEnabled() ) {
				fare=rule.apply(current, last, fare);
			}
		}
		for(FareRule rule :rules) {
			if((rule instanceof R4|| rule instanceof R5) &&rule.isEnabled() ) {
				fare=rule.apply(current, last, fare);
			}
		}
		return fare;
	}
}
public class FareEngine {
	public static void main(String[] args) {
		List<Tap> taps = new ArrayList<>();
		taps.add(new Tap("07-01 07:20","G","BD"));
		taps.add(new Tap("07-01 08:01","G","NC"));
		taps.add(new Tap("07-01 08:30","R","YH"));
		taps.add(new Tap("07-01 08:32","Y","YH"));
		taps.add(new Tap("07-01 10:01","R","KL"));
		taps.add(new Tap("07-01 10:28","Y","NC"));
		taps.add(new Tap("07-01 10:32","Y","JT"));
		taps.add(new Tap("07-01 14:36","G","NC"));
		taps.add(new Tap("07-01 22:15","Y","BD"));
		taps.add(new Tap("07-01 23:58","G","NC"));
		taps.add(new Tap("07-02 00:45","X","NC"));
		taps.add(new Tap("07-02 01:10","G","BD"));
		taps.add(new Tap("07-02 04:01","G","BD"));
		taps.add(new Tap("07-02 13:05","Y","JT"));
		taps.add(new Tap("07-02 13:15","G","KL"));
		taps.add(new Tap("07-02 13:36","G","JT"));
		taps.add(new Tap("07-02 18:02","Y","BD"));
		taps.add(new Tap("07-02 18:18","Y","NC"));
		taps.add(new Tap("07-02 20:01","G","KL"));
		taps.add(new Tap("07-02 20:15","R","YT"));
		taps.add(new Tap("07-02 22:02","Y","KL"));
		taps.add(new Tap("07-02 23:15","G","BD"));
		taps.add(new Tap("07-03 00:20","R","NC"));
		boolean eR1=true;
		boolean eR2=true;
		boolean eR3=true;
		boolean eR4=true;
		boolean eR5=true;
	TariffEngine engine = new TariffEngine();
	engine.addrule(new R1(25,eR1));
	engine.addrule(new R2(1.5,eR2));
	engine.addrule(new R3(30,eR3));
	engine.addrule(new R4(20,eR4));
	engine.addrule(new R5(35,eR5));
	Tap lastTap=null;
	for(Tap tap:taps) {
		tap.fare=engine.calculateFare(tap, lastTap);
		lastTap=tap;
		System.out.println(tap);
	}
}}

