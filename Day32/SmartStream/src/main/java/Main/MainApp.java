package Main;
import SchedulingStratergy.BalancedStratergy;
import SchedulingStratergy.GreedyEngagementStrategy;
import SchedulingStratergy.GreedyRevenueStrategy;
import SchedulingStratergy.SchedulingStratergy;
public class MainApp{
    public static void main(String[] args) {
        SmartStreamScheduler scheduler = new SmartStreamScheduler();
//        SchedulingStratergy currentStrategy = new GreedyRevenueStrategy();
//         SchedulingStratergy currentStrategy = new GreedyEngagementStrategy();
         SchedulingStratergy currentStrategy = new BalancedStratergy();
        System.out.println("Running scheduling with strategy: " + currentStrategy.getName());
        SchedulingReport report = scheduler.scheduleAllDays(currentStrategy);
        report.printReport();
    }
}
