package model;

public enum Day {
    DAY1(false),
    DAY2(true), // Saturday
    DAY3(true), // Sunday
    DAY4(false),
    DAY5(false);

    private final boolean isWeekend;

    Day(boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public boolean isWeekend() {
        return isWeekend;
    }
}
