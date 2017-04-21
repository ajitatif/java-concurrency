package org.turkisi.training.concurrency;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class VolatileAndSyncrhonized {

    private volatile int i = 0;

    private final Object lock = new Object();

    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                i = i + 1;
                System.out.println("Thread 1. Current value: " + i);
            }
        }
    };

    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                i = i - 1;
                System.out.println("Thread 2. Current value: " + i);
            }
        }
    };

    private void go() throws InterruptedException {

        while (true) {
            i = 0;
            new Thread(runnable1).start();
            new Thread(runnable2).start();
            Thread.sleep(200);
        }
    }

    public static void main(String[] args) {
        try {
            new VolatileAndSyncrhonized().go();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
