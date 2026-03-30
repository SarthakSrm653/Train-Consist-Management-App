import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - Train_management_App
 * ============================================================
 * Use Case 12: Safety Compliance Check for Goods Bogies
 * Description: Uses allMatch() to ensure Cylindrical bogies
 * only carry Petroleum.
 */
public class Train_management_App {

    static class GoodsBogie {
        String type;  // e.g., "Cylindrical", "Open", "Box"
        String cargo; // e.g., "Petroleum", "Coal", "Grain"

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return String.format("[%s Bogie carrying %s]", type, cargo);
        }
    }

    public static void main(String[] args) {
        List<GoodsBogie> goodsConsist = new ArrayList<>();

        // Test Case: Adding a mix of bogies
        goodsConsist.add(new GoodsBogie("Open", "Coal"));
        goodsConsist.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsConsist.add(new GoodsBogie("Box", "Grain"));

        // Uncomment the line below to test an UNSAFE train
        // goodsConsist.add(new GoodsBogie("Cylindrical", "Coal"));

        System.out.println("==============================================");
        System.out.println(" UC12 - Safety Compliance Check (allMatch) ");
        System.out.println("==============================================\n");

        System.out.println("Current Goods Consist:");
        goodsConsist.forEach(System.out::println);

        // --- SAFETY VALIDATION LOGIC ---
        // Rule: IF type is Cylindrical, cargo MUST be Petroleum.
        // Logic: (Not Cylindrical) OR (Is Cylindrical AND cargo is Petroleum)
        boolean isSafe = goodsConsist.stream().allMatch(bogie -> {
            if (bogie.type.equalsIgnoreCase("Cylindrical")) {
                return bogie.cargo.equalsIgnoreCase("Petroleum");
            }
            return true; // Non-cylindrical bogies pass by default here
        });

        System.out.println("\n----------------------------------------------");
        if (isSafe) {
            System.out.println("STATUS: ✔ SAFE TO DEPART");
            System.out.println("All safety protocols for cylindrical bogies met.");
        } else {
            System.out.println("STATUS: ❌ UNSAFE - STOP TRAIN");
            System.out.println("Violation: Cylindrical bogie detected with illegal cargo!");
        }
        System.out.println("----------------------------------------------");

        System.out.println("\nUC12 safety check completed.");
    }
}