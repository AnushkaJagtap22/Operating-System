import java.util.Scanner;

public class SequentialAllocation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] memory = new int[50]; // 50 disk blocks (0 = free, 1 = occupied)

        System.out.print("Enter number of files: ");
        int files = sc.nextInt();

        for (int i = 0; i < files; i++) {
            System.out.print("\nEnter starting block: ");
            int start = sc.nextInt();

            System.out.print("Enter length of file: ");
            int length = sc.nextInt();

            boolean allocated = true;

            // Check if blocks are free
            for (int j = start; j < start + length; j++) {
                if (memory[j] == 1) {
                    allocated = false;
                    break;
                }
            }

            // Allocate if possible
            if (allocated) {
                for (int j = start; j < start + length; j++) {
                    memory[j] = 1;
                }

                System.out.print("File allocated at blocks: ");
                for (int j = start; j < start + length; j++) {
                    System.out.print(j + " ");
                }
                System.out.println();
            } else {
                System.out.println("Allocation failed (blocks not contiguous/free)");
            }
        }
    }
}