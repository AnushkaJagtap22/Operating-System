// ReadersWriters.java
class SharedResource {
    private int readCount = 0;

    // Reader enters
    public synchronized void startReading(String readerName) {
        readCount++;
        System.out.println(readerName + " is READING. Readers count = " + readCount);
    }

    // Reader exits
    public synchronized void stopReading(String readerName) {
        readCount--;
        System.out.println(readerName + " has STOPPED reading. Readers count = " + readCount);
    }

    // Writer enters (exclusive)
    public synchronized void startWriting(String writerName) {
        System.out.println(writerName + " is WRITING...");
        try {
            Thread.sleep(1000); // simulate writing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Writer exits
    public synchronized void stopWriting(String writerName) {
        System.out.println(writerName + " has FINISHED writing.");
    }
}

// Reader Thread
class Reader extends Thread {
    SharedResource resource;

    Reader(SharedResource resource, String name) {
        super(name);
        this.resource = resource;
    }

    public void run() {
        resource.startReading(getName());
        try {
            Thread.sleep(500); // simulate reading
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.stopReading(getName());
    }
}

// Writer Thread
class Writer extends Thread {
    SharedResource resource;

    Writer(SharedResource resource, String name) {
        super(name);
        this.resource = resource;
    }

    public void run() {
        resource.startWriting(getName());
        resource.stopWriting(getName());
    }
}

// Main Class
public class ReadersWriters {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Reader r1 = new Reader(resource, "Reader-1");
        Reader r2 = new Reader(resource, "Reader-2");
        Writer w1 = new Writer(resource, "Writer-1");

        r1.start();
        r2.start();
        w1.start();
    }
}