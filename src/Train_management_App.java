import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - UseCase7TrainConsistMgmnt
 * ============================================================
 * Use Case 7: Sort Bogies by Capacity (Comparator)
 * * Description:
 * This class sorts passenger bogies based on seating 
 * capacity using a custom Comparator.
 */
public class Train_management_App {

    // Inner Bogie class to model passenger bogies
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
        Scanner scanner = new Scanner(System.in);
        List<Bogie> bogies = new ArrayList<>();

        System.out.println("==============================================");
        System.out.println(" UC7 - Sort Bogies by Capacity (Comparator) ");
        System.out.println("==============================================\n");

        // Taking User Input
        System.out.print("Enter the number of bogies to add: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < count; i++) {
            System.out.print("Enter Bogie Name (e.g., Sleeper, AC Chair): ");
            String name = scanner.nextLine();
            System.out.print("Enter Seating Capacity: ");
            int cap = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            bogies.add(new Bogie(name, cap));
        }

        // Display Unsorted Data
        System.out.println("\nBefore Sorting:");
        bogies.forEach(System.out::println);

        // Sorting using Comparator (comparingInt for efficiency)
        // This arranges them from lowest capacity to highest
        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        // Display Sorted Result
        System.out.println("\nAfter Sorting by Capacity:");
        bogies.forEach(System.out::println);

        System.out.println("\nUC7 sorting completed...");
        scanner.close();
    }
}