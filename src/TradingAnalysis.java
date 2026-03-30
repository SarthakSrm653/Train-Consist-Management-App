import java.util.Arrays;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class TradingAnalysis {

    // --- MERGE SORT (Stable, O(n log n) Guaranteed) ---
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Stable: <= ensures original order for equal volumes
            if (arr[i].volume <= arr[j].volume) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (i = 0; i < temp.length; i++) arr[left + i] = temp[i];
    }

    // --- QUICK SORT (In-place, DESC, Average O(n log n)) ---
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        // Using Lomuto Partition with high element as pivot
        int pivot = arr[high].volume;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            // For DESC order: check if volume is GREATER than pivot
            if (arr[j].volume >= pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // --- MERGE TWO SORTED LISTS ---
    public static Trade[] combineSessions(Trade[] session1, Trade[] session2) {
        Trade[] merged = new Trade[session1.length + session2.length];
        int i = 0, j = 0, k = 0;
        while (i < session1.length && j < session2.length) {
            if (session1[i].volume <= session2[j].volume) merged[k++] = session1[i++];
            else merged[k++] = session2[j++];
        }
        while (i < session1.length) merged[k++] = session1[i++];
        while (j < session2.length) merged[k++] = session2[j++];
        return merged;
    }

    public static void main(String[] args) {
        Trade[] morning = { new Trade("T3", 500), new Trade("T1", 100), new Trade("T2", 300) };
        Trade[] afternoon = { new Trade("T4", 400), new Trade("T5", 200) };

        // 1. Merge Sort Ascending
        mergeSort(morning, 0, morning.length - 1);
        System.out.println("MergeSort (Asc): " + Arrays.toString(morning));

        // 2. Quick Sort Descending
        quickSort(morning, 0, morning.length - 1);
        System.out.println("QuickSort (Desc): " + Arrays.toString(morning));

        // 3. Merge Morning and Afternoon
        mergeSort(afternoon, 0, afternoon.length - 1);
        mergeSort(morning, 0, morning.length - 1); // Re-sort morning for combining
        Trade[] totalMarket = combineSessions(morning, afternoon);

        // 4. Compute Total Volume
        long totalVol = 0;
        for (Trade t : totalMarket) totalVol += t.volume;

        System.out.println("Combined Market Trades: " + Arrays.toString(totalMarket));
        System.out.println("Total Volume: " + totalVol);
    }
}