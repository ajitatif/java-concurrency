package org.turkisi.training.concurrency;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class RaceCondition {

    private int i = 0;

    private Thread incrementer = new Thread() {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Incremented index. Current value: " + ++i);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Thread decrementer = new Thread() {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Decremented index. Current value: " + --i);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private void go() throws InterruptedException {

        System.out.println("Starting incrementer thread");
        incrementer.start();
        Thread.sleep(2000);
        System.out.println("Starting decrementer thread");
        decrementer.start();
    }

    public static void main(String[] args) {
        try {
            new RaceCondition().go();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
