package org.turkisi.training.concurrency;

import java.util.Random;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class DoubleCheck {

    private static Random random = new Random();

    private int theNumber;

    private final Object lock = new Object();

    public static void main(String[] args) {

        new DoubleCheck().shoot();
    }

    private void shoot() {

        new Thread(() -> {

            while (true) {
                System.out.println("Randomizer thread loop start");
                synchronized (lock) {
                    theNumber = random.nextInt(100);
                    System.out.println("Randomizer thread: " + theNumber);
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println("Even-izer Thread loop start");
                if (theNumber % 2 == 1) {
                    synchronized (lock) {
                        if (theNumber % 2 == 1) {
                            theNumber++;
                            System.out.println("Even-izer Thread: " + theNumber);
                        }
                    }
                }
                System.out.println("Even-izer Thread loop end");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
