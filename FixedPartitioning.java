import java.util.Scanner;

public class FixedPartitioning {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of partitions: ");
        int n = sc.nextInt();

        int[] partitions = new int[n];
        System.out.println("Enter partition sizes:");
        for (int i = 0; i < n; i++) {
            partitions[i] = sc.nextInt();
        }

        System.out.print("Enter number of processes: ");
        int m = sc.nextInt();

        int[] processes = new int[m];
        System.out.println("Enter process sizes:");
        for (int i = 0; i < m; i++) {
            processes[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            boolean allocated = false;

            for (int j = 0; j < n; j++) {
                if (processes[i] <= partitions[j]) {
                    System.out.println("Process " + (i + 1) +
                            " allocated to Partition " + (j + 1));
                    partitions[j] = -1; // occupied
                    allocated = true;
                    break;
                }
            }

            if (!allocated)
                System.out.println("Process " + (i + 1) + " NOT allocated");
        }
    }
}