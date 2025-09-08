package SchedulingStratergy;

import model.AdjustContentItems; 
 import java.util.Comparator;

 public interface SchedulingStratergy {
     Comparator<AdjustContentItems> getComparator(); 
     String getName();
 }