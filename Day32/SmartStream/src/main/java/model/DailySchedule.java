package model;


import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
@Getter

public class DailySchedule {
    private final Day day;
    private final List<AdjustContentItems> scheduledItems;
    private double totalRevenue;
    private int totalEngagement;
    private final List<String> dailyNotes;

    public DailySchedule(Day day) {
        this.day = day;
        this.scheduledItems = new ArrayList<>();
        this.totalRevenue = 0;
        this.totalEngagement = 0;
        this.dailyNotes = new ArrayList<>();
    }

    public void addScheduledItem(AdjustContentItems item) {
        this.scheduledItems.add(item);
        this.totalRevenue += item.getAdjustedRevenue();
        this.totalEngagement += item.getAdjustedEngagement();
        if (!item.getNotes().isEmpty()) {
            this.dailyNotes.add(item.getContentItem().getId() + " (" + item.getContentItem().getTitle() + ") " + item.getNotes());
        }
    }

    
}