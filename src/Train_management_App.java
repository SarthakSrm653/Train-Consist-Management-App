import java.util.*;
import java.util.stream.Collectors;

public class Train_management_App {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return name + " -> " + capacity;
        }
    }

    public static void main(String[] args) {
        List<Bogie> bogies = new ArrayList<>();

        // Adding sample data as per UC7 requirements
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        System.out.println("==============================================");
        System.out.println(" UC8 - Filter Bogies (Capacity > 60) ");
        System.out.println("==============================================\n");

        System.out.println("Original List:");
        bogies.forEach(System.out::println);

        // --- STREAM API LOGIC ---
        // 1. Convert list to stream
        // 2. Filter bogies where capacity > 60
        // 3. Collect the results into a new list
        List<Bogie> highCapacityBogies = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered Bogies (Capacity > 60):");
        if (highCapacityBogies.isEmpty()) {
            System.out.println("No bogies match the criteria.");
        } else {
            highCapacityBogies.forEach(System.out::println);
        }

        // Verify Original Integrity
        System.out.println("\nVerification: Original list size remains " + bogies.size());
        System.out.println("UC8 filtering completed...");
    }
}