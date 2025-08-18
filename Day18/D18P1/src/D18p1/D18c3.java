package D18p1;
class Car1 extends Thread {
    private volatile boolean suspended = false; // flag to control suspend

    public void run() {
        for (int i = 0; i <= 10; i++) {
            try {
                synchronized (this) {
                    while (suspended) {
                        wait(); // wait until resume() is called
                    }
                }

                if (Thread.currentThread().getName().equals("BMW")) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                if (Thread.currentThread().getName().equals("Benz")) {
                    System.out.println("\t" + Thread.currentThread().getName() + ": " + i);
                }

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Custom suspend method
    public void pauseThread() {
        suspended = true;
    }

    // Custom resume method
    public synchronized void resumeThread() {
        suspended = false;
        notify(); // wake up waiting thread
    }
}

public class D18c3 {
    public static void main(String[] args) {
        Car1 car = new Car1();

        Thread t1 = new Thread(car, "BMW");
        Thread t2 = new Thread(car, "Benz");

        t1.start();
        t2.start();

        for (int i = 0; i <= 10; i++) {
            try {
                System.out.println("           K:" + i);

                if (i == 4) {
                    car.pauseThread();   // pause both threads
                    System.out.println("🚫 BMW thread paused");
                }
                if (i == 6) {
                    car.resumeThread();  // resume both threads
                    System.out.println("▶ BMW thread resumed");
                }

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
