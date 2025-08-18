package D18p1;

class Worker extends Thread {
    private boolean running = true;

    public Worker(String name) {
        super(name); // set thread name
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 1; i <= 5; i++) {
                if (!running) break;

                System.out.println(getName() + " [Priority " + getPriority() + "] working... step " + i);

                try {
                    // yield() lets other threads run
                    if (i == 2) {
                        System.out.println(getName() + " yielding CPU...");
                        Thread.yield();
                    }

                    // wait() temporarily pauses thread
                    if (i == 3) {
                        System.out.println(getName() + " waiting...");
                        wait(1000); // auto-resumes after 1s
                    }

                    Thread.sleep(500); // slow down for clarity
                } catch (InterruptedException e) {
                    System.out.println(getName() + " interrupted!");
                }
            }
            System.out.println(getName() + " finished work.");
        }
    }

    // Custom stop method
    public void stopThread() {
        running = false;
    }
}

public class D18c4 {
    public static void main(String[] args) {
        Worker t1 = new Worker("Thread-1");
        Worker t2 = new Worker("Thread-2");

        // Setting priorities (1 = MIN, 5 = NORM, 10 = MAX)
        t1.setPriority(Thread.MIN_PRIORITY);   // 1
        t2.setPriority(Thread.MAX_PRIORITY);   // 10

        System.out.println("Before start: Thread-1 alive? " + t1.isAlive());

        // Start threads
        t1.start();
        t2.start();

        System.out.println("After start: Thread-1 alive? " + t1.isAlive());
        System.out.println("Thread-1 priority: " + t1.getPriority());
        System.out.println("Thread-2 priority: " + t2.getPriority());

        try {
            Thread.sleep(2000);

            // notify threads (in case they are waiting)
            synchronized (t1) {
                System.out.println("Main notifying Thread-1");
                t1.notify();
            }
            synchronized (t2) {
                System.out.println("Main notifying Thread-2");
                t2.notify();
            }

            // Stop t1 gracefully
            t1.stopThread();

            // Wait for threads to finish
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("After join: Thread-1 alive? " + t1.isAlive());
        System.out.println("Main thread finished.");
    }
}
