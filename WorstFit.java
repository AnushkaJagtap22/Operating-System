import java.util.Scanner;

public class WorstFit {
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
            int worstIndex = -1;

            for (int j = 0; j < n; j++) {
                if (processes[i] <= blocks[j]) {
                    if (worstIndex == -1 || blocks[j] > blocks[worstIndex]) {
                        worstIndex = j;
                    }
                }
            }

            if (worstIndex != -1) {
                System.out.println("Process " + (i + 1) +
                        " allocated to Block " + (worstIndex + 1));
                blocks[worstIndex] -= processes[i];
            } else {
                System.out.println("Process " + (i + 1) + " NOT allocated");
            }
        }
    }
}