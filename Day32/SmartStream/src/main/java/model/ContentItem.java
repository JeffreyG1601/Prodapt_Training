package model;



import java.util.Objects;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ContentItem {
    private String id;
    private String title;
    private int durationMinutes;
    private MonetizationType monetization;
    private Genre genre; 
    private double baseRevenue;
    private int baseEngagement;
    private boolean premium;
    private Set<Day> releaseWindow;
    private int maxRuns;

    @Override
    public String toString() {
        return id + " (" + title + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentItem that = (ContentItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}