// DeadlockDetection.java
import java.util.*;

public class DeadlockDetection {
    public static void main(String[] args) {
        int n = 5; // processes
        int m = 3; // resources

        int[][] allocation = {
            {0, 1, 0},
            {2, 0, 0},
            {3, 0, 3},
            {2, 1, 1},
            {0, 0, 2}
        };

        int[][] request = {
            {0, 0, 0},
            {2, 0, 2},
            {0, 0, 0},
            {1, 0, 0},
            {0, 0, 2}
        };

        int[] available = {0, 0, 0};

        boolean[] finish = new boolean[n];

        // Mark processes with no allocation as finished
        for (int i = 0; i < n; i++) {
            boolean allocated = false;
            for (int j = 0; j < m; j++) {
                if (allocation[i][j] != 0) {
                    allocated = true;
                    break;
                }
            }
            finish[i] = !allocated;
        }

        int[] work = Arrays.copyOf(available, m);

        boolean progress;
        do {
            progress = false;

            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (request[i][j] > work[j])
                            break;
                    }

                    if (j == m) {
                        for (int k = 0; k < m; k++) {
                            work[k] += allocation[i][k];
                        }

                        finish[i] = true;
                        progress = true;
                    }
                }
            }
        } while (progress);

        boolean deadlock = false;

        for (int i = 0; i < n; i++) {
            if (!finish[i]) {
                deadlock = true;
                System.out.println("Process P" + i + " is in deadlock.");
            }
        }

        if (!deadlock) {
            System.out.println("No deadlock detected.");
        }
    }
}