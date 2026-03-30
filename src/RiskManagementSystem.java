import java.util.Arrays;
import java.util.Scanner;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return String.format("%s (Risk: %d, Bal: $%.2f)", name, riskScore, accountBalance);
    }
}

public class RiskManagementSystem {

    // 1. Bubble Sort: Ascending by Risk Score
    // Includes swap visualization for demo purposes
    public static void bubbleSortAscending(Client[] clients) {
        int n = clients.length;
        int totalSwaps = 0;
        System.out.println("\n--- Bubble Sort Demo (Ascending Risk) ---");

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    System.out.println("  Swap: " + clients[j].name + " <-> " + clients[j+1].name);
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    totalSwaps++;
                }
            }
        }
        System.out.println("Bubble Sort Complete. Total Swaps: " + totalSwaps);
    }

    // 2. Insertion Sort: Descending by Risk Score + Account Balance
    // This is adaptive and excels if data is already nearly sorted
    public static void insertionSortDescending(Client[] clients) {
        int n = clients.length;
        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;

            /* Condition: Shift if:
               1. key riskScore is GREATER than current (for descending)
               2. riskScores are EQUAL but key balance is HIGHER
            */
            while (j >= 0 && shouldShift(clients[j], key)) {
                clients[j + 1] = clients[j];
                j = j - 1;
            }
            clients[j + 1] = key;
        }
    }

    private static boolean shouldShift(Client current, Client key) {
        if (key.riskScore > current.riskScore) return true;
        if (key.riskScore == current.riskScore) {
            return key.accountBalance > current.accountBalance;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of clients to rank: ");
        int n = sc.nextInt();
        Client[] clients = new Client[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nClient " + (i + 1));
            System.out.print("Name: ");
            String name = sc.next();
            System.out.print("Risk Score (0-100): ");
            int score = sc.nextInt();
            System.out.print("Account Balance: ");
            double balance = sc.nextDouble();
            clients[i] = new Client(name, score, balance);
        }

        // Execution
        bubbleSortAscending(Arrays.copyOf(clients, clients.length));

        System.out.println("\n--- Final Risk Ranking (Descending) ---");
        insertionSortDescending(clients);

        // Output Top 10 (or all if less than 10)
        int topCount = Math.min(n, 10);
        System.out.println("Top " + topCount + " Priority Reviews:");
        for (int i = 0; i < topCount; i++) {
            System.out.println((i + 1) + ". " + clients[i]);
        }

        sc.close();
    }
}