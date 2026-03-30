import java.util.*;

/**
 * ============================================================
 * CUSTOM EXCEPTION CLASS
 * ============================================================
 */
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

/**
 * ============================================================
 * MAIN CLASS - Train_management_App
 * ============================================================
 * Use Case 14: Handle Invalid Bogie Capacity (Custom Exception)
 * Description: Prevents creation of bogies with capacity <= 0
 * using a user-defined checked exception.
 */
public class Train_management_App {

    static class PassengerBogie {
        String type;
        int capacity;

        // Constructor enforcing business rules via Custom Exception
        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Invalid Capacity: " + capacity +
                        ". Capacity must be greater than zero.");
            }
            this.type = type;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return String.format("[%s Bogie | Capacity: %d]", type, capacity);
        }
    }

    public static void main(String[] args) {
        List<PassengerBogie> consist = new ArrayList<>();

        System.out.println("==============================================");
        System.out.println(" UC14 - Custom Exception Handling (Fail-Fast) ");
        System.out.println("==============================================\n");

        // --- TEST CASE 1: Valid Bogie ---
        try {
            System.out.println("Attempting to create Sleeper with 72 seats...");
            consist.add(new PassengerBogie("Sleeper", 72));
            System.out.println("✔ Successfully added.");
        } catch (InvalidCapacityException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }

        System.out.println();

        // --- TEST CASE 2: Invalid Bogie (Zero Capacity) ---
        try {
            System.out.println("Attempting to create AC Chair with 0 seats...");
            consist.add(new PassengerBogie("AC Chair", 0));
        } catch (InvalidCapacityException e) {
            System.out.println("❌ Caught Expected Exception: " + e.getMessage());
        }

        System.out.println();

        // --- TEST CASE 3: Invalid Bogie (Negative Capacity) ---
        try {
            System.out.println("Attempting to create First Class with -10 seats...");
            consist.add(new PassengerBogie("First Class", -10));
        } catch (InvalidCapacityException e) {
            System.out.println("❌ Caught Expected Exception: " + e.getMessage());
        }

        // Display Final Consist
        System.out.println("\n--- Final Validated Consist ---");
        if (consist.isEmpty()) {
            System.out.println("No valid bogies added.");
        } else {
            consist.forEach(System.out::println);
        }

        System.out.println("\nUC14 exception handling completed.");
    }
}