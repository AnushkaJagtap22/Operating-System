// Writer.java
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class Writer {
    public static void main(String[] args) {
        try {
            // Create or open file
            RandomAccessFile file = new RandomAccessFile("shared_memory.txt", "rw");
            FileChannel channel = file.getChannel();

            // Map file into memory
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

            // Take input
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter data: ");
            String data = sc.nextLine();

            // Write to shared memory
            buffer.put(data.getBytes());

            System.out.println("Data written successfully.");

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}