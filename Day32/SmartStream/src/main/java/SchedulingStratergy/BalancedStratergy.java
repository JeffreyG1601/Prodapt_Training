package SchedulingStratergy;

import model.AdjustContentItems;
import java.util.Comparator;
public class BalancedStratergy implements SchedulingStratergy {
  private static final double REVENUE_WEIGHT = 0.6;
  private static final double ENGAGEMENT_WEIGHT = 0.4;

  @Override
  public Comparator<AdjustContentItems> getComparator() {
      return Comparator.comparingDouble((AdjustContentItems item) ->
          (item.getAdjustedRevenue() * REVENUE_WEIGHT) +
          (item.getAdjustedEngagement() * ENGAGEMENT_WEIGHT)
      ).reversed();
  }

  @Override
  public String getName() { return "Balanced (0.6*Rev + 0.4*Eng)"; }
}