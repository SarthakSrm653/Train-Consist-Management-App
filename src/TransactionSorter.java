import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s: fee=%.2f, ts=%s]", id, fee, timestamp);
    }
}

public class TransactionSorter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Transaction> transactions = new ArrayList<>();

        System.out.println("--- Banking Audit Input System ---");
        System.out.print("Enter number of transactions to process: ");
        int count = sc.nextInt();
        sc.nextLine(); // Consume newline

        for (int i = 0; i < count; i++) {
            System.out.println("\nTransaction #" + (i + 1));
            System.out.print("Enter ID (e.g., id1): ");
            String id = sc.nextLine();

            System.out.print("Enter Fee (e.g., 10.50): ");
            double fee = sc.nextDouble();
            sc.nextLine(); // Consume newline

            System.out.print("Enter Timestamp (e.g., 10:00): ");
            String ts = sc.nextLine();

            transactions.add(new Transaction(id, fee, ts));
        }

        // Logic Switcher based on Batch Size
        if (count <= 100) {
            System.out.println("\n--- Small Batch Detected: Running Bubble Sort ---");
            bubbleSort(new ArrayList<>(transactions));
        } else {
            System.out.println("\n--- Medium Batch Detected: Running Insertion Sort ---");
            insertionSort(new ArrayList<>(transactions));
        }

        flagOutliers(transactions);
        sc.close();
    }

    // Bubble Sort: Optimized with 'swapped' flag
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int swaps = 0;
        int passes = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break;
        }
        System.out.println("Sorted: " + list);
        System.out.println("Stats: " + passes + " passes, " + swaps + " swaps.");
    }

    // Insertion Sort: Stable sort by Fee + Timestamp
    public static void insertionSort(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Compare fee, then timestamp if fees are equal
            while (j >= 0 && (list.get(j).fee > key.fee ||
                    (list.get(j).fee == key.fee && list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        System.out.println("Sorted (Fee+TS): " + list);
    }

    public static void flagOutliers(List<Transaction> list) {
        System.out.print("\nHigh-fee outliers (> $50): ");
        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50.0) {
                System.out.print(t.id + " ($" + t.fee + ") ");
                found = true;
            }
        }
        if (!found) System.out.print("None");
        System.out.println();
    }
}