import java.util.Scanner;

public class SRTF {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] bt = new int[n];
        int[] rt = new int[n];
        int[] wt = new int[n];
        int[] tat = new int[n];
        int[] at = new int[n];

        System.out.println("Enter Arrival Time:");

        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + ": ");
            at[i] = sc.nextInt();
        }

        System.out.println("Enter Burst Time:");

        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        int complete = 0, t = 0, min = Integer.MAX_VALUE;
        int shortest = 0;
        boolean check = false;

        while (complete != n) {

            for (int j = 0; j < n; j++) {
                if ((at[j] <= t) && (rt[j] < min) && rt[j] > 0) {
                    min = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (!check) {
                t++;
                continue;
            }

            rt[shortest]--;

            min = rt[shortest];
            if (min == 0)
                min = Integer.MAX_VALUE;

            if (rt[shortest] == 0) {

                complete++;
                check = false;

                int finish_time = t + 1;

                wt[shortest] = finish_time - bt[shortest] - at[shortest];

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }

            t++;
        }

        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }

        System.out.println("\nProcess\tAT\tBT\tWT\tTAT");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }

        sc.close();
    }
}