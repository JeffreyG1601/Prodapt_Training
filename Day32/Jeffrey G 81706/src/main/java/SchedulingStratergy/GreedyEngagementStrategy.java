package SchedulingStratergy;

import model.AdjustContentItems;
import java.util.Comparator;
public class GreedyEngagementStrategy implements SchedulingStratergy {
    @Override
    public Comparator<AdjustContentItems> getComparator() {
        return Comparator.comparingInt(AdjustContentItems::getAdjustedEngagement).reversed();
    }

    @Override
    public String getName() {
        return "Greedy by Engagement";
    }
}