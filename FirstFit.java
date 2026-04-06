import java.util.Scanner;

public class FirstFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of blocks: ");
        int n = sc.nextInt();

        int[] blocks = new int[n];
        System.out.println("Enter block sizes:");
        for (int i = 0; i < n; i++) {
            blocks[i] = sc.nextInt();
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
                if (processes[i] <= blocks[j]) {
                    System.out.println("Process " + (i + 1) +
                            " allocated to Block " + (j + 1));
                    blocks[j] -= processes[i];
                    allocated = true;
                    break;
                }
            }

            if (!allocated)
                System.out.println("Process " + (i + 1) + " NOT allocated");
        }
    }
}
