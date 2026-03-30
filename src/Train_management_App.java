import java.util.*;
import java.util.stream.Collectors;

/**
 * ============================================================
 * MAIN CLASS - Train_management_App
 * ============================================================
 * Use Case 10: Count Total Seats in Train (reduce)
 * Description: Aggregates seating capacities into a single
 * total value using Stream map() and reduce().
 */
public class Train_management_App {

    // Bogie model class
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public int getCapacity() {
            return capacity;
        }

        @Override
        public String toString() {
            return String.format("[%s: %d seats]", name, capacity);
        }
    }

    public static void main(String[] args) {
        // 1. Setup - Create the list of bogies
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 72)); // Another sleeper bogie

        System.out.println("==============================================");
        System.out.println(" UC10 - Count Total Seats in Train (reduce) ");
        System.out.println("==============================================\n");

        System.out.println("Current Train Consist:");
        bogies.forEach(System.out::println);

        // --- STREAM AGGREGATION LOGIC ---
        // 1. stream() - Starts the pipeline
        // 2. map() - Extracts only the integers (capacities)
        // 3. reduce() - Sums them up starting from 0
        int totalSeats = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        // Display results
        System.out.println("\n----------------------------------------------");
        System.out.println("Total Seating Capacity: " + totalSeats);
        System.out.println("----------------------------------------------");

        // Verification of Original List Integrity
        System.out.println("\nVerification: Original list size is still " + bogies.size());
        System.out.println("UC10 aggregation completed successfully.");
    }
}