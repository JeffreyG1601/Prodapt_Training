package D23p1;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
class Tap {
   LocalDateTime timestamp;
   String line;
   String station;
   double fare;
   public Tap(String datetime, String line, String station) {
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	   this.timestamp = LocalDateTime.parse("2025-" + datetime, formatter);

       this.line = line;
       this.station = station;
       this.fare = 0;
   }
   public String toString() {
       return timestamp + " | " + line + " | " + station + " | Fare: " + fare;
   }
}
interface FareRule {
   double apply(Tap current, Tap last, double currentFare);
   boolean isEnabled();
}
class BaseFareRule implements FareRule {
   boolean enabled;
   double baseFare;
   public BaseFareRule(double baseFare, boolean enabled) {
       this.baseFare = baseFare;
       this.enabled = enabled;
   }
   public boolean isEnabled() { return enabled; }
   public double apply(Tap current, Tap last, double currentFare) {
       if (!enabled) return currentFare;
       return baseFare;
   }
}
class PeakPeriodRule implements FareRule {
   boolean enabled;
   LocalTime morningStart = LocalTime.of(8, 0);
   LocalTime morningEnd = LocalTime.of(10, 0);
   LocalTime eveningStart = LocalTime.of(18, 0);
   LocalTime eveningEnd = LocalTime.of(20, 0);
   double peakMultiplier;
   public PeakPeriodRule(double peakMultiplier, boolean enabled) {
       this.peakMultiplier = peakMultiplier;
       this.enabled = enabled;
   }
   public boolean isEnabled() { return enabled; }
   public double apply(Tap current, Tap last, double currentFare) {
       if (!enabled || currentFare == 0) return currentFare;
       LocalTime time = current.timestamp.toLocalTime();
       if ((time.isAfter(morningStart.minusSeconds(1)) && time.isBefore(morningEnd.plusSeconds(1))) ||
           (time.isAfter(eveningStart.minusSeconds(1)) && time.isBefore(eveningEnd.plusSeconds(1)))) {
           return currentFare * peakMultiplier;
       }
       return currentFare;
   }
}
class TransferWindowRule implements FareRule {
   boolean enabled;
   int windowMinutes;
   public TransferWindowRule(int windowMinutes, boolean enabled) {
       this.windowMinutes = windowMinutes;
       this.enabled = enabled;
   }
   public boolean isEnabled() { return enabled; }
   public double apply(Tap current, Tap last, double currentFare) {
       if (!enabled) return currentFare;
       if (last == null) return currentFare;
       long minutesBetween = java.time.Duration.between(last.timestamp, current.timestamp).toMinutes();
       if (minutesBetween <= windowMinutes) return 0;
       return currentFare;
   }
}
class NightDiscountRule implements FareRule {
   boolean enabled;
   LocalTime start = LocalTime.of(10, 0);
   LocalTime end = LocalTime.of(23, 59);
   double discountPercent;
   public NightDiscountRule(double discountPercent, boolean enabled) {
       this.discountPercent = discountPercent;
       this.enabled = enabled;
   }
   public boolean isEnabled() { return enabled; }
   public double apply(Tap current, Tap last, double currentFare) {
       if (!enabled || currentFare == 0) return currentFare;
       LocalTime time = current.timestamp.toLocalTime();
       if (!time.isBefore(start) && !time.isAfter(end)) {
           return currentFare * (1 - discountPercent / 100.0);
       }
       return currentFare;
   }
}
class PostMidnightDiscountRule implements FareRule {
   boolean enabled;
   LocalTime start = LocalTime.of(0, 0);
   LocalTime end = LocalTime.of(4, 0);
   double discountPercent;
   public PostMidnightDiscountRule(double discountPercent, boolean enabled) {
       this.discountPercent = discountPercent;
       this.enabled = enabled;
   }
   public boolean isEnabled() { return enabled; }
   public double apply(Tap current, Tap last, double currentFare) {
       if (!enabled || currentFare == 0) return currentFare;
       LocalTime time = current.timestamp.toLocalTime();
       if (!time.isBefore(start) && time.isBefore(end.plusSeconds(1))) {
           return currentFare * (1 - discountPercent / 100.0);
       }
       return currentFare;
   }
}
// ----- TariffEngine -----
class TariffEngine {
   List<FareRule> rules;
   public TariffEngine() {
       rules = new ArrayList<>();
   }
   public void addRule(FareRule rule) {
       rules.add(rule);
   }
   public double calculateFare(Tap current, Tap last) {
       double fare = 0;
       // Base fare first
       for (FareRule rule : rules) {
           if (rule instanceof BaseFareRule && rule.isEnabled())
               fare = rule.apply(current, last, fare);
       }
       // Transfer window next
       for (FareRule rule : rules) {
           if (rule instanceof TransferWindowRule && rule.isEnabled())
               fare = rule.apply(current, last, fare);
       }
       // Peak fare
       for (FareRule rule : rules) {
           if (rule instanceof PeakPeriodRule && rule.isEnabled())
               fare = rule.apply(current, last, fare);
       }
       // Discounts
       for (FareRule rule : rules) {
           if ((rule instanceof NightDiscountRule || rule instanceof PostMidnightDiscountRule) && rule.isEnabled())
               fare = rule.apply(current, last, fare);
       }
       return fare;
   }
}
// ----- Main Application -----
public class FareBox{
   public static void main(String[] args) {
       List<Tap> taps = new ArrayList<>();
       // ----- Manual Tap Entries -----
       taps.add(new Tap("07-01 07:20", "G", "BD"));
       taps.add(new Tap("07-01 08:01", "G", "NC"));
       taps.add(new Tap("07-01 08:30", "R", "YH"));
       taps.add(new Tap("07-01 08:32", "Y", "YH"));
       taps.add(new Tap("07-01 10:01", "R", "KL"));
       taps.add(new Tap("07-01 10:28", "Y", "NC"));
       taps.add(new Tap("07-01 10:32", "Y", "JT"));
       taps.add(new Tap("07-01 14:36", "G", "NC"));
       taps.add(new Tap("07-01 22:15", "Y", "BD"));
       taps.add(new Tap("07-01 23:58", "G", "NC"));
       taps.add(new Tap("07-02 00:45", "X", "NC"));
       taps.add(new Tap("07-02 01:10", "G", "BD"));
       taps.add(new Tap("07-02 04:01", "G", "BD"));
       taps.add(new Tap("07-02 13:05", "Y", "JT"));
       taps.add(new Tap("07-02 13:15", "G", "KL"));
       taps.add(new Tap("07-02 13:36", "G", "JT"));
       taps.add(new Tap("07-02 18:02", "Y", "BD"));
       taps.add(new Tap("07-02 18:18", "Y", "NC"));
       taps.add(new Tap("07-02 20:01", "G", "YT"));
       taps.add(new Tap("07-02 20:15", "R", "KL"));
       taps.add(new Tap("07-02 22:02", "Y", "BD"));
       taps.add(new Tap("07-02 23:15", "G", "NC"));
       taps.add(new Tap("07-03 00:20", "R", "NC"));
       // ----- Dynamic Rule Toggles -----
       boolean enableBaseFare = true;
       boolean enablePeak = true;
       boolean enableTransfer = true;
       boolean enableNightDiscount = true;
       boolean enablePostMidnightDiscount = false;
       // ----- Initialize Tariff Engine -----
       TariffEngine engine = new TariffEngine();
       engine.addRule(new BaseFareRule(25, enableBaseFare));
       engine.addRule(new PeakPeriodRule(1.5, enablePeak));
       engine.addRule(new TransferWindowRule(30, enableTransfer));
       engine.addRule(new NightDiscountRule(20, enableNightDiscount));
       engine.addRule(new PostMidnightDiscountRule(35, enablePostMidnightDiscount));
       // ----- Calculate Fares -----
       Tap lastTap = null;
       for (Tap tap : taps) {
           tap.fare = engine.calculateFare(tap, lastTap);
           lastTap = tap;
           System.out.println(tap);
       }
   }
}