import java.util.Scanner;

public class IndexedAllocation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] memory = new int[50]; // disk blocks

        System.out.print("Enter number of files: ");
        int files = sc.nextInt();

        for (int i = 0; i < files; i++) {
            System.out.print("\nEnter index block: ");
            int indexBlock = sc.nextInt();

            if (memory[indexBlock] == 1) {
                System.out.println("Index block already allocated!");
                continue;
            }

            System.out.print("Enter number of blocks needed: ");
            int n = sc.nextInt();

            int[] blocks = new int[n];
            System.out.println("Enter block numbers:");

            boolean allocated = true;

            for (int j = 0; j < n; j++) {
                blocks[j] = sc.nextInt();

                if (memory[blocks[j]] == 1) {
                    allocated = false;
                }
            }

            if (allocated) {
                memory[indexBlock] = 1;

                System.out.print("File allocated\nIndex Block: " + indexBlock + "\nBlocks: ");
                for (int j = 0; j < n; j++) {
                    memory[blocks[j]] = 1;
                    System.out.print(blocks[j] + " ");
                }
                System.out.println();
            } else {
                System.out.println("Allocation failed (block already used)");
            }
        }
    }
}