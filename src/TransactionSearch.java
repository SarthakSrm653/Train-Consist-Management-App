import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Transaction {
    String accountId;
    String txId;

    public Transaction(String accountId, String txId) {
        this.accountId = accountId;
        this.txId = txId;
    }

    @Override
    public String toString() {
        return accountId + "(" + txId + ")";
    }
}

public class TransactionSearch {

    // --- LINEAR SEARCH: O(n) ---
    // Best for: Unsorted data or finding the absolute first occurrence
    public static int linearSearch(Transaction[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].accountId.equals(target)) {
                System.out.println("Linear Search: Found at index " + i + " after " + comparisons + " comparisons.");
                return i;
            }
        }
        return -1;
    }

    // --- BINARY SEARCH: O(log n) ---
    // Requires: Sorted data. Optimized for massive datasets.
    public static int binarySearch(Transaction[] logs, String target) {
        int low = 0;
        int high = logs.length - 1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int res = target.compareTo(logs[mid].accountId);

            if (res == 0) {
                System.out.println("Binary Search: Found at index " + mid + " after " + comparisons + " comparisons.");
                return mid;
            }
            if (res > 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // --- COUNT OCCURRENCES (Audit Helper) ---
    public static int countOccurrences(Transaction[] logs, String target) {
        // First find any occurrence via Binary Search
        int index = binarySearch(logs, target);
        if (index == -1) return 0;

        int count = 1;
        // Check to the left
        int left = index - 1;
        while (left >= 0 && logs[left].accountId.equals(target)) {
            count++;
            left--;
        }
        // Check to the right
        int right = index + 1;
        while (right < logs.length && logs[right].accountId.equals(target)) {
            count++;
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        // Sample Input (Sorted for Binary Search)
        Transaction[] logs = {
                new Transaction("accA", "TX01"),
                new Transaction("accB", "TX02"),
                new Transaction("accB", "TX03"),
                new Transaction("accC", "TX04")
        };

        String target = "accB";

        // Execution
        linearSearch(logs, target);
        int total = countOccurrences(logs, target);

        System.out.println("Audit Result: Account " + target + " appeared " + total + " times.");
    }
}