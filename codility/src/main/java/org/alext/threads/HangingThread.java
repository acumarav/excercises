package org.alext.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by alext on 3/30/2017.
 */
public class HangingThread {

    private static Object LOCK = new Object();

    public int runCalculations(int scale) {

        List<CompletableFuture> futures = new ArrayList<>();
        for (int index = 0; index < scale; index++) {
            final int finalIndex = index;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> this.calculate(finalIndex));

            futures.add(future);
        }

        System.out.println("tasks was submit");

        int sum = 0;
        for (CompletableFuture<Integer> future : futures) {
            try {
                int val = future.get(1000, TimeUnit.MILLISECONDS);
                sum += val;

            } catch (Exception e) {
                future.completeExceptionally(new InterruptedException());
            }
        }

        return sum;
    }


    private int calculate(int value) {
        try {
            int pause = (int) Math.random() * 10;
            Thread.sleep(pause * 100);

            if (value == 13) {
                System.out.println(Thread.currentThread().getName() + "- try to acquire LOCK");
                synchronized (LOCK) {
                    System.out.println("LOCK acquired by: " + Thread.currentThread().getName());
                    Thread.sleep(60 * 60 * 1000);
                }

            }

            return value;
        } catch (InterruptedException ex) {
            System.out.println(Thread.currentThread().getName() + " thread was interrupted");
            return -1;
        }
    }

}
