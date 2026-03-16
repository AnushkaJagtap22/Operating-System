import java.util.Scanner;

public class FCFS {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] bt = new int[n];  // Burst Time
        int[] wt = new int[n];  // Waiting Time
        int[] tat = new int[n]; // Turnaround Time

        System.out.println("Enter Burst Time for each process:");

        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
        }

        wt[0] = 0;

        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + bt[i - 1];
        }

        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + bt[i];
        }

        System.out.println("\nProcess\tBT\tWT\tTAT");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }

        sc.close();
    }
}