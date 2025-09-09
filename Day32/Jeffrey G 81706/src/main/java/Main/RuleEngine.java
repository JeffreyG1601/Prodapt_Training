package Main;
import exception.ContentNotInWindowException;
import exception.MaxRunsExceededException;
import model.*;
import java.util.Map;
public class RuleEngine {
    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }
    public AdjustContentItems applyRules(ContentItem contentItem, ScheduleContext context,
                                          Map<String, Integer> runCounts, Map<Genre, Integer> dailyGenreCounts)
            throws ContentNotInWindowException, MaxRunsExceededException {
        if (!contentItem.getReleaseWindow().contains(context.getCurrentDay())) {
            throw new ContentNotInWindowException("Content " + contentItem.getId() + " is not in its release window for " + context.getCurrentDay());
        }

        int currentRuns = runCounts.getOrDefault(contentItem.getId(), 0);
        if (currentRuns >= contentItem.getMaxRuns()) {
            throw new MaxRunsExceededException("Content " + contentItem.getId() + " has exceeded its max runs (" + contentItem.getMaxRuns() + ")");
        }
        double adjustedRevenue = contentItem.getBaseRevenue();
        int adjustedEngagement = contentItem.getBaseEngagement();
        AdjustContentItems adjustedItem = new AdjustContentItems(contentItem, adjustedRevenue, adjustedEngagement);
        if (context.getCurrentDay().isWeekend()) {
            adjustedEngagement += 8;
            adjustedItem.addNote("Weekend engagement +8");
        }
        if (contentItem.getGenre() == Genre.KIDS && context.isKidsTime()) {
            adjustedEngagement += 10;
            adjustedItem.addNote("Kids Time engagement +10");
        }
        if (contentItem.getMonetization() == MonetizationType.PPV && context.isPrimeTime()) {
            adjustedRevenue *= 1.25;
            adjustedItem.addNote("PPV Prime-Time revenue x1.25");
        }
        if (contentItem.getMonetization() == MonetizationType.PPV && context.getCurrentDay().isWeekend()) {
            adjustedRevenue *= 1.15;
            adjustedItem.addNote("Weekend PPV revenue x1.15");
        }
        int genreCountForDay = dailyGenreCounts.getOrDefault(contentItem.getGenre(), 0);
        if (genreCountForDay >= 4) {
            adjustedEngagement -= 8;
            adjustedItem.addNote("Diversity cap hit for " + contentItem.getGenre() + " (engagement -8)");
        }
        adjustedEngagement = clamp(adjustedEngagement, 0, 100);
        adjustedItem.setAdjustedRevenue(adjustedRevenue);
        adjustedItem.setAdjustedEngagement(adjustedEngagement);
        return adjustedItem;
    }
}