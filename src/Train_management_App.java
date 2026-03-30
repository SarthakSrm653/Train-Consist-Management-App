import java.util.*;
import java.util.stream.Collectors;

/**
 * ============================================================
 * MAIN CLASS - UseCase9TrainConsistMgmnt
 * ============================================================
 * Use Case 9: Group Bogies by Type
 * * Description:
 * This class categorizes bogies into a Map structure
 * using Collectors.groupingBy().
 */
public class Train_management_App {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "[Name: " + name + ", Cap: " + capacity + "]";
        }
    }

    public static void main(String[] args) {
        // 1. Create a list of bogies (including duplicates to test grouping)
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("AC Chair", 56));

        System.out.println("==============================================");
        System.out.println(" UC9 - Group Bogies by Type (Collectors) ");
        System.out.println("==============================================\n");

        System.out.println("Original Unordered List:");
        bogies.forEach(System.out::println);

        // 2. Apply groupingBy logic
        // This creates a Map<String, List<Bogie>>
        Map<String, List<Bogie>> groupedBogies = bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));

        // 3. Display the structured results
        System.out.println("\nGrouped Bogie Structure:");
        groupedBogies.forEach((type, list) -> {
            System.out.println(type + " -> " + list);
        });

        System.out.println("\nUC9 grouping completed successfully.");
    }
}