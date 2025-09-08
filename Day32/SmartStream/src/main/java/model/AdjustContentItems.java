package model;

import lombok.Getter;
import java.util.Objects; 
 @Getter 
 public class AdjustContentItems { 
     private final ContentItem contentItem;
     private double adjustedRevenue;
     private int adjustedEngagement;
     private String notes; 

     public AdjustContentItems(ContentItem contentItem, double adjustedRevenue, int adjustedEngagement) {
         this.contentItem = contentItem;
         this.adjustedRevenue = adjustedRevenue;
         this.adjustedEngagement = adjustedEngagement;
         this.notes = "";
     }

     public void setAdjustedRevenue(double adjustedRevenue) {
         this.adjustedRevenue = adjustedRevenue;
     }

     public void setAdjustedEngagement(int adjustedEngagement) {
         this.adjustedEngagement = adjustedEngagement;
     }

     public void addNote(String note) {
         if (!this.notes.isEmpty()) {
             this.notes += "; ";
         }
         this.notes += note;
     }
     public String getId() {
         return contentItem.getId();
     }

     public Genre getGenre() {
         return contentItem.getGenre();
     }
     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         AdjustContentItems that = (AdjustContentItems) o;
         return Objects.equals(contentItem.getId(), that.contentItem.getId()); 
     }
     @Override
     public int hashCode() {
         return Objects.hash(contentItem.getId()); 
     }
 }