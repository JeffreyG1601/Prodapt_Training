package model;

import lombok.Getter;

@Getter
public class ScheduleContext {
    private final Day currentDay;
    private final int slotIndex;
    private final boolean isKidsTime;
    private final boolean isPrimeTime;

    public ScheduleContext(Day currentDay, int slotIndex) {
        this.currentDay = currentDay;
        this.slotIndex = slotIndex;
        this.isKidsTime = slotIndex >= 0 && slotIndex <= 2;
        this.isPrimeTime = slotIndex >= 6 && slotIndex <= 9;
    }
}