import java.util.*;
import java.util.stream.Collectors;

/**
 * ============================================================
 * MAIN CLASS - Train_management_App
 * ============================================================
 * Use Case 13: Performance Comparison (Loops vs Streams)
 * Description: Benchmarks the execution time of filtering
 * logic using System.nanoTime().
 */
public class Train_management_App {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public int getCapacity() { return capacity; }
    }

    public static void main(String[] args) {
        // 1. Prepare a large dataset to make the time difference visible
        List<Bogie> bogies = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            bogies.add(new Bogie("Bogie-" + i, random.nextInt(100)));
        }

        System.out.println("==============================================");
        System.out.println(" UC13 - Performance Comparison (Loops vs Streams) ");
        System.out.println(" Benchmark Dataset Size: " + bogies.size() + " Bogies");
        System.out.println("==============================================\n");

        // --- APPROACH 1: TRADITIONAL FOR-LOOP ---
        long startLoop = System.nanoTime();
        List<Bogie> filteredByLoop = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.getCapacity() > 60) {
                filteredByLoop.add(b);
            }
        }
        long endLoop = System.nanoTime();
        long loopDuration = endLoop - startLoop;

        // --- APPROACH 2: JAVA STREAMS ---
        long startStream = System.nanoTime();
        List<Bogie> filteredByStream = bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());
        long endStream = System.nanoTime();
        long streamDuration = endStream - startStream;

        // 2. Display Results
        System.out.println("Results for Capacity > 60:");
        System.out.println("Loop Filtered Count  : " + filteredByLoop.size());
        System.out.println("Stream Filtered Count: " + filteredByStream.size());

        System.out.println("\n--- Performance Benchmarking ---");
        System.out.println("Loop Execution Time  : " + loopDuration + " ns");
        System.out.println("Stream Execution Time: " + streamDuration + " ns");

        // 3. Simple Analysis
        if (loopDuration < streamDuration) {
            System.out.println("\nVerdict: Traditional Loop was faster by " + (streamDuration - loopDuration) + " ns");
        } else {
            System.out.println("\nVerdict: Stream was faster by " + (loopDuration - streamDuration) + " ns");
        }

        System.out.println("\nUC13 benchmarking completed.");
    }
}