package Main;
import model.AdjustContentItems;
import model.DailySchedule;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class SchedulingReport {
    private final List<DailySchedule> dailySchedules;
    private final List<String> missedContentMessages;
    private double weeklyTotalRevenue;
    private int weeklyTotalEngagement;
    public SchedulingReport() {
        this.dailySchedules = new ArrayList<>();
        this.missedContentMessages = new ArrayList<>();
        this.weeklyTotalRevenue = 0;
        this.weeklyTotalEngagement = 0;
    }
    public List<String> getMissedContentMessages() {
        return missedContentMessages;
    }

    public void addDailySchedule(DailySchedule schedule) {
        this.dailySchedules.add(schedule);
        this.weeklyTotalRevenue += schedule.getTotalRevenue();
        this.weeklyTotalEngagement += schedule.getTotalEngagement();
    }

    public void addMissedContent(String message) {
        this.missedContentMessages.add(message);
    }

    public void printReport() {
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("=== SmartStream Scheduling Report ===");

        for (DailySchedule dailySchedule : dailySchedules) {
            System.out.println("\n" + dailySchedule.getDay() + (dailySchedule.getDay().isWeekend() ? " (Weekend)" : " (Weekday)") + ":");
            List<String> scheduledIds = dailySchedule.getScheduledItems().stream()
                    .map(AdjustContentItems::getId)
                    .collect(Collectors.toList());
            System.out.println("Scheduled: " + scheduledIds);
            System.out.println("Total Revenue = ₹" + df.format(dailySchedule.getTotalRevenue()));
            System.out.println("Total Engagement = " + dailySchedule.getTotalEngagement());
            if (!dailySchedule.getDailyNotes().isEmpty()) {
                System.out.println("Notes:");
                dailySchedule.getDailyNotes().forEach(note -> System.out.println("  - " + note));
            }
        }
        System.out.println("\n-------------------------------------");
        System.out.println("WEEKLY TOTALS");
        System.out.println("Total Revenue = ₹" + df.format(weeklyTotalRevenue));
        System.out.println("Total Engagement = " + weeklyTotalEngagement);
        if (!missedContentMessages.isEmpty()) {
            System.out.println("\nMissed Content:");
            missedContentMessages.forEach(msg -> System.out.println("- " + msg));
        }
        System.out.println("=====================================");
    }
}