package D21p1;

import java.util.*;

class Player {
    int jerseyNo;
    String name;
    String role;

    Player(int jerseyNo, String name, String role) {
        this.jerseyNo = jerseyNo;
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "#" + jerseyNo + " " + name + " (" + role + ")";
    }
}

public class D21c11 {
    private static ArrayList<Player> playingXI = new ArrayList<>();
    private static ArrayList<Player> bench = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-define Playing XI
        playingXI.add(new Player(45, "Rohit Sharma", "Captain"));
        playingXI.add(new Player(18, "Virat Kohli", "Batsman"));
        playingXI.add(new Player(77, "Shubman Gill", "Batsman"));
        playingXI.add(new Player(17, "Rishabh Pant", "WicketKeeper"));
        playingXI.add(new Player(33, "Hardik Pandya", "AllRounder"));
        playingXI.add(new Player(93, "Ravindra Jadeja", "AllRounder"));
        playingXI.add(new Player(99, "Jasprit Bumrah", "Bowler"));
        playingXI.add(new Player(11, "Mohammed Shami", "Bowler"));
        playingXI.add(new Player(23, "Kuldeep Yadav", "Bowler"));
        playingXI.add(new Player(3, "Yuzvendra Chahal", "Bowler"));
        playingXI.add(new Player(20, "Axar Patel", "Bowler"));

        // Pre-define Bench (5)
        bench.add(new Player(63, "Ishan Kishan", "WicketKeeper"));
        bench.add(new Player(7, "MS Dhoni", "Batsman"));
        bench.add(new Player(16, "Washington Sundar", "AllRounder"));
        bench.add(new Player(12, "Umran Malik", "Bowler"));
        bench.add(new Player(54, "Prasidh Krishna", "Bowler"));

        while (true) {
            System.out.println("\n--- Cricket Team Management ---");
            System.out.println("1. View Playing XI");
            System.out.println("2. View Bench");
            System.out.println("3. Replace Injured Player");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: viewTeam(playingXI, "Playing XI"); break;
                case 2: viewTeam(bench, "Bench"); break;
                case 3: replaceInjuredPlayer(); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // View a team (playing XI or bench)
    private static void viewTeam(List<Player> team, String title) {
        System.out.println("\n--- " + title + " ---");
        for (Player p : team) {
            System.out.println(p);
        }
    }

    // Replace injured player
    private static void replaceInjuredPlayer() {
        System.out.print("Enter Injured Player Jersey No: ");
        int jersey = sc.nextInt();
        sc.nextLine();

        Player injured = null;
        for (Player p : playingXI) {
            if (p.jerseyNo == jersey) {
                injured = p;
                break;
            }
        }

        if (injured == null) {
            System.out.println("❌ No such player in Playing XI!");
            return;
        }

        // Find replacement from bench (same role)
        Player replacement = null;
        for (Player b : bench) {
            if (b.role.equalsIgnoreCase(injured.role)) {
                replacement = b;
                break;
            }
        }

        if (replacement == null) {
            System.out.println("❌ No bench player available for role: " + injured.role);
            return;
        }

        // Perform replacement
        playingXI.remove(injured);
        playingXI.add(replacement);
        bench.remove(replacement);
        bench.add(injured);

        System.out.println("✅ Replaced " + injured + " with " + replacement);
        viewTeam(playingXI, "Updated Playing XI");
    }
}
