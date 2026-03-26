// Reader.java
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Reader {
    public static void main(String[] args) {
        try {
            // Open same file
            RandomAccessFile file = new RandomAccessFile("shared_memory.txt", "r");
            FileChannel channel = file.getChannel();

            // Map file into memory
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, 1024);

            // Read data
            byte[] data = new byte[1024];
            buffer.get(data);

            System.out.println("Data read from shared memory:");
            System.out.println(new String(data).trim());

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}