package Main;

import exception.ContentNotInWindowException;
import exception.MaxRunsExceededException;
import model.*;
import SchedulingStratergy.SchedulingStratergy; 
import java.util.*;
import java.util.stream.Collectors;

public class SmartStreamScheduler {

    private final List<ContentItem> contentCatalog;
    private final Map<String, Integer> globalRunCounts;
    private final RuleEngine ruleEngine;
    private final int SLOTS_PER_DAY = 10;

    public SmartStreamScheduler() {
        this.contentCatalog = initializeCatalog();
        this.globalRunCounts = new HashMap<>();
        this.ruleEngine = new RuleEngine();
    }

    private List<ContentItem> initializeCatalog() {
        return Arrays.asList(
                new ContentItem("C1", "Blockbuster Movie", 120, MonetizationType.SUBSCRIPTION, Genre.MOVIE, 200, 95, true, EnumSet.of(Day.DAY1, Day.DAY2, Day.DAY3), 2),
                new ContentItem("C2", "Sports Match (Live)", 180, MonetizationType.PPV, Genre.SPORTS, 400, 90, true, EnumSet.of(Day.DAY2), 1),
                new ContentItem("C3", "Original Series Ep1", 45, MonetizationType.SUBSCRIPTION, Genre.ORIGINAL, 150, 85, true, EnumSet.of(Day.DAY1, Day.DAY2, Day.DAY3, Day.DAY4, Day.DAY5), 2),
                new ContentItem("C4", "Kids Cartoon Hour", 60, MonetizationType.AD, Genre.KIDS, 60, 70, false, EnumSet.of(Day.DAY1, Day.DAY2, Day.DAY3, Day.DAY4, Day.DAY5), 5),
                new ContentItem("C5", "Music Concert Special", 90, MonetizationType.PPV, Genre.MUSIC, 300, 88, true, EnumSet.of(Day.DAY4, Day.DAY5), 1),
                new ContentItem("C6", "Regional Drama", 60, MonetizationType.AD, Genre.DRAMA, 80, 75, false, EnumSet.of(Day.DAY1, Day.DAY2, Day.DAY3, Day.DAY4, Day.DAY5), 3),
                new ContentItem("C7", "Documentary Feature", 75, MonetizationType.SUBSCRIPTION, Genre.DOCUMENTARY, 120, 78, true, EnumSet.of(Day.DAY2, Day.DAY3, Day.DAY4, Day.DAY5), 2),
                new ContentItem("C8", "Stand-up Comedy Night", 60, MonetizationType.AD, Genre.COMEDY, 90, 82, false, EnumSet.of(Day.DAY3, Day.DAY4, Day.DAY5), 2),
                new ContentItem("C9", "Originals: Ep2", 45, MonetizationType.SUBSCRIPTION, Genre.ORIGINAL, 160, 86, true, EnumSet.of(Day.DAY2, Day.DAY3, Day.DAY4, Day.DAY5), 2),
                new ContentItem("C10", "Regional Music Show", 45, MonetizationType.AD, Genre.MUSIC, 70, 72, false, EnumSet.of(Day.DAY1, Day.DAY2, Day.DAY3, Day.DAY4, Day.DAY5), 3)
        );
    }
    public SchedulingReport scheduleAllDays(SchedulingStratergy strategy) {
        SchedulingReport report = new SchedulingReport();
        this.globalRunCounts.clear();
        Set<String> permanentlyRejectedContentIds = new HashSet<>();
        Set<String> uniqueMissedMessages = new HashSet<>();
        for (Day currentDay : Day.values()) {
            DailySchedule dailySchedule = new DailySchedule(currentDay);
            Map<Genre, Integer> dailyGenreCounts = new HashMap<>();
            
            String lastScheduledItemId = null;

            for (int slotIndex = 0; slotIndex < SLOTS_PER_DAY; slotIndex++) {
                ScheduleContext context = new ScheduleContext(currentDay, slotIndex);

                final String previousSlotId = lastScheduledItemId;

                List<AdjustContentItems> eligibleCandidates = contentCatalog.stream()
                        .filter(item -> !item.getId().equals(previousSlotId))
                        .filter(item -> !permanentlyRejectedContentIds.contains(item.getId()))
                        .map(item -> {
                            try {
                                return ruleEngine.applyRules(item, context, globalRunCounts, dailyGenreCounts);
                            } catch (ContentNotInWindowException | MaxRunsExceededException e) {
                                if (e instanceof MaxRunsExceededException) {
                                    permanentlyRejectedContentIds.add(item.getId());
                                    uniqueMissedMessages.add(item.getId() + " (" + item.getTitle() + ") could not repeat (MaxRuns=" + item.getMaxRuns() + ")");
                                }
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                eligibleCandidates.sort(strategy.getComparator());

                if (!eligibleCandidates.isEmpty()) {
                    AdjustContentItems selectedItem = eligibleCandidates.get(0);
                    dailySchedule.addScheduledItem(selectedItem);

                    lastScheduledItemId = selectedItem.getId();

                    globalRunCounts.merge(selectedItem.getId(), 1, Integer::sum);
                    dailyGenreCounts.merge(selectedItem.getGenre(), 1, Integer::sum);
                }
            }
            report.addDailySchedule(dailySchedule);
        }

        uniqueMissedMessages.forEach(report::addMissedContent);
        return report;
    }
}