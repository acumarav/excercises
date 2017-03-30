package org.alext.threads;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by alext on 3/30/2017.
 */
public class HangingThread {

    private static Object LOCK = new Object();

    private ForkJoinPool executors = ForkJoinPool.commonPool();


    public int runFutureCalculations(int scale) {
        //ExecutorService executors = Executors.newCachedThreadPool();

        List<MyTask> tasks = new ArrayList<>();
        for (int index = 0; index < scale; index++) {

            MyTask task = new MyTask(index);
            tasks.add(task);
        }

        return processFutures(tasks);
    }

    private int processFutures(List<MyTask> futures) {

        System.out.println("processing: " + futures.size());

        futures.forEach(fut -> executors.execute(fut));

        List<MyTask> failed = new ArrayList<>();

        int sum = 0;
        for (MyTask task : futures) {

            try {
                int val = task.get(20, TimeUnit.MILLISECONDS);
                sum += val;

            } catch (Throwable e) {
                task.cancel(true);
                failed.add(task.cloneTask());
            }
        }

        if (failed.isEmpty()) {
            return sum;
        } else {
            return sum + processFutures(failed);
        }
    }

    /*public int runCalculations(int scale) {

        List<CompletableFuture> futures = new ArrayList<>();
        for (int index = 0; index < scale; index++) {
            final int finalIndex = index;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> this.calculate(finalIndex)).exceptionally(er -> {
                System.out.println("Thread interruption");
                Thread.currentThread().interrupt();
                return -1;
            });

            futures.add(future);
        }

        System.out.println("tasks was submit");

        int sum = 0;
        for (CompletableFuture<Integer> future : futures) {
            try {
                int val = future.get(100, TimeUnit.MILLISECONDS);
                sum += val;

            } catch (Throwable e) {


                future.cancel(true);
                future.join();

                echoPool();
            }
        }

        return sum;
    }*/

    private static void echoPool() {
        System.out.println("Pool State: " + ForkJoinPool.commonPool().toString());
    }


    private static int calculate(int value) {
        try {
            int pause = (int) Math.random() * 10;
            Thread.sleep(pause * 100);

            double rnd = Math.random() * 10;

            if (rnd >= 6.0) {
                System.out.println(Thread.currentThread().getName() + "- try to acquire LOCK");
                synchronized (LOCK) {
                    System.out.println("LOCK acquired by: " + Thread.currentThread().getName());
                    Thread.sleep(60 * 60 * 1000);
                }

            }

            return value;
        } catch (Exception ex) {
            System.out.println(Thread.currentThread().getName() + " thread was interrupted");
            return -1;
        }
    }


    private class MyTask extends FutureTask<Integer> {

        private final int value;

        MyTask(int val) {
            super(() -> HangingThread.calculate(val));
            this.value = val;
        }

        private MyTask cloneTask() {
            return new MyTask(this.value);
        }
    }

}


