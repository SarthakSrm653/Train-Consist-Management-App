import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ============================================================
 * MAIN CLASS - Train_management_App
 * ============================================================
 * Use Case 11: Validate Train ID & Cargo Codes (Regex)
 * Description: Uses Pattern and Matcher to enforce strict
 * formatting rules for Train IDs and Cargo Codes.
 */
public class Train_management_App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Define Regex Patterns
        // TRN- followed by exactly 4 digits
        String trainIdRegex = "TRN-\\d{4}";
        // PET- followed by exactly 2 uppercase letters
        String cargoCodeRegex = "PET-[A-Z]{2}";

        // 2. Compile Patterns
        Pattern trainPattern = Pattern.compile(trainIdRegex);
        Pattern cargoPattern = Pattern.compile(cargoCodeRegex);

        System.out.println("==============================================");
        System.out.println(" UC11 - Train ID & Cargo Code Validation ");
        System.out.println("==============================================\n");

        // --- TRAIN ID VALIDATION ---
        System.out.print("Enter Train ID (Format: TRN-1234): ");
        String inputTrainId = scanner.nextLine();
        Matcher trainMatcher = trainPattern.matcher(inputTrainId);

        if (trainMatcher.matches()) {
            System.out.println("✔ Valid Train ID: " + inputTrainId);
        } else {
            System.out.println("❌ Invalid Train ID! Must follow 'TRN-xxxx' (4 digits).");
        }

        System.out.println();

        // --- CARGO CODE VALIDATION ---
        System.out.print("Enter Cargo Code (Format: PET-AB): ");
        String inputCargoCode = scanner.nextLine();
        Matcher cargoMatcher = cargoPattern.matcher(inputCargoCode);

        if (cargoMatcher.matches()) {
            System.out.println("✔ Valid Cargo Code: " + inputCargoCode);
        } else {
            System.out.println("❌ Invalid Cargo Code! Must follow 'PET-XX' (2 uppercase letters).");
        }

        System.out.println("\nUC11 validation completed...");
        scanner.close();
    }
}