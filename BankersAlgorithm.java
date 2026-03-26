// BankersAlgorithm.java
import java.util.*;

public class BankersAlgorithm {
    public static void main(String[] args) {
        int n = 5; // processes
        int m = 3; // resources

        int[][] allocation = {
            {0, 1, 0},
            {2, 0, 0},
            {3, 0, 2},
            {2, 1, 1},
            {0, 0, 2}
        };

        int[][] max = {
            {7, 5, 3},
            {3, 2, 2},
            {9, 0, 2},
            {2, 2, 2},
            {4, 3, 3}
        };

        int[] available = {3, 3, 2};

        int[][] need = new int[n][m];

        // Calculate Need matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        boolean[] finish = new boolean[n];
        int[] safeSeq = new int[n];
        int count = 0;

        int[] work = Arrays.copyOf(available, m);

        while (count < n) {
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > work[j])
                            break;
                    }

                    if (j == m) {
                        for (int k = 0; k < m; k++) {
                            work[k] += allocation[i][k];
                        }

                        safeSeq[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("System is NOT in safe state.");
                return;
            }
        }

        System.out.println("System is in SAFE state.");
        System.out.print("Safe Sequence: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + safeSeq[i] + " ");
        }
    }
}