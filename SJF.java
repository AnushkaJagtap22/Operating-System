import java.util.Scanner;

public class SJF {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] bt = new int[n];
        int[] wt = new int[n];
        int[] tat = new int[n];
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = i + 1;
        }

        System.out.println("Enter Burst Time:");

        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if (bt[i] > bt[j]) {

                    int temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }
            }
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
            System.out.println("P" + p[i] + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }

        sc.close();
    }
}