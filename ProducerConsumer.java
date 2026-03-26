// ProducerConsumer.java
import java.util.concurrent.Semaphore;

// Shared Buffer Class
class Buffer {
    private int[] buffer;
    private int size;
    private int in = 0, out = 0;

    Semaphore empty;
    Semaphore full;
    Semaphore mutex;

    Buffer(int size) {
        this.size = size;
        buffer = new int[size];

        empty = new Semaphore(size); // initially all slots empty
        full = new Semaphore(0);     // no items initially
        mutex = new Semaphore(1);    // binary semaphore
    }

    // Produce item
    public void produce(int item) throws InterruptedException {
        empty.acquire();   // wait if no empty space
        mutex.acquire();   // enter critical section

        buffer[in] = item;
        System.out.println("Produced: " + item);
        in = (in + 1) % size;

        mutex.release();   // exit critical section
        full.release();    // increase filled slots
    }

    // Consume item
    public void consume() throws InterruptedException {
        full.acquire();    // wait if no item
        mutex.acquire();   // enter critical section

        int item = buffer[out];
        System.out.println("Consumed: " + item);
        out = (out + 1) % size;

        mutex.release();   // exit critical section
        empty.release();   // increase empty slots
    }
}

// Producer Thread
class Producer extends Thread {
    Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.produce(i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Consumer Thread
class Consumer extends Thread {
    Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.consume();
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Main Class
public class ProducerConsumer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(3); // buffer size = 3

        Producer p = new Producer(buffer);
        Consumer c = new Consumer(buffer);

        p.start();
        c.start();
    }
}