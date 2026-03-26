import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, tq;

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        int[] bt = new int[n];  // Burst Time
        int[] rt = new int[n];  // Remaining Time
        int[] at = new int[n];  // Arrival Time
        int[] ct = new int[n];  // Completion Time
        int[] tat = new int[n]; // Turnaround Time
        int[] wt = new int[n];  // Waiting Time

        // Input Burst Time
        System.out.println("Enter Burst Time for each process:");
        for(int i = 0; i < n; i++) {
            System.out.print("P" + (i+1) + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i]; // initially same
            at[i] = 0;     // assuming all arrival times = 0
        }

        // Input Time Quantum
        System.out.print("Enter Time Quantum: ");
        tq = sc.nextInt();

        int time = 0;
        int remain = n;

        // Round Robin Logic
        while(remain > 0) {
            for(int i = 0; i < n; i++) {
                if(rt[i] > 0) {
                    if(rt[i] <= tq) {
                        time += rt[i];
                        ct[i] = time;
                        rt[i] = 0;
                        remain--;
                    } else {
                        time += tq;
                        rt[i] -= tq;
                    }
                }
            }
        }

        // Calculate TAT and WT
        for(int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        // Output
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for(int i = 0; i < n; i++) {
            System.out.println("P" + (i+1) + "\t" + at[i] + "\t" + bt[i] + "\t"
                    + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        sc.close();
    }
}