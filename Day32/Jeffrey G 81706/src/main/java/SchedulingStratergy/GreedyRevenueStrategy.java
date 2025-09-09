package SchedulingStratergy;


import model.AdjustContentItems;

import java.util.Comparator;

public class GreedyRevenueStrategy implements SchedulingStratergy {
    @Override
    public Comparator<AdjustContentItems> getComparator() {
        return Comparator.comparingDouble(AdjustContentItems::getAdjustedRevenue).reversed();
    }

    @Override
    public String getName() {
        return "Greedy by Revenue";
    }
}