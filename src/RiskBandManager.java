import java.util.Arrays;

public class RiskBandManager {

    // --- LINEAR SEARCH: Unsorted Threshold Match ---
    public static void linearSearch(int[] bands, int threshold) {
        int comparisons = 0;
        boolean found = false;
        for (int i = 0; i < bands.length; i++) {
            comparisons++;
            if (bands[i] == threshold) {
                System.out.println("Linear: Match found at index " + i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Linear: Threshold " + threshold + " not found (" + comparisons + " comparisons)");
        }
    }

    // --- BINARY SEARCH: Floor and Ceiling Logic ---
    // Floor: Largest element <= target
    // Ceiling: Smallest element >= target
    public static void findRiskBands(int[] sortedBands, int target) {
        int low = 0, high = sortedBands.length - 1;
        int floor = -1, ceiling = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;

            if (sortedBands[mid] == target) {
                floor = ceiling = sortedBands[mid];
                break;
            } else if (sortedBands[mid] < target) {
                floor = sortedBands[mid]; // Potential floor
                low = mid + 1;
            } else {
                ceiling = sortedBands[mid]; // Potential ceiling
                high = mid - 1;
            }
        }

        System.out.println("Binary Results for " + target + ":");
        System.out.println(" - Floor (Largest <= " + target + "): " + (floor == -1 ? "None" : floor));
        System.out.println(" - Ceiling (Smallest >= " + target + "): " + (ceiling == -1 ? "None" : ceiling));
        System.out.println(" - Performance: " + comparisons + " comparisons");
    }

    public static void main(String[] args) {
        // Sample Input
        int[] riskBands = {10, 25, 50, 100};
        int threshold = 30;

        // 1. Linear Search (on the array as is)
        linearSearch(riskBands, threshold);

        // 2. Binary Search for Floor/Ceiling
        // (Array must be sorted - it is in this case)
        findRiskBands(riskBands, threshold);
    }
}