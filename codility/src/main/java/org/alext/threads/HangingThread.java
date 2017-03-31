package org.alext.threads;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by alext on 3/30/2017.
 */
public class HangingThread {

    private static Object LOCK = new Object();

    private ForkJoinPool executors = ForkJoinPool.commonPool();
    //ExecutorService executors = Executors.newCachedThreadPool();


    public int runFutureCalculations(int scale) {
        List<IndexTask<Integer>> tasks = new ArrayList<>();
        for (int index = 0; index < scale; index++) {

            int finalIndex = index;
            IndexTask<Integer> task = new IndexTask(index, () -> calculate(finalIndex));
            tasks.add(task);
        }

        Integer[] results = new Integer[tasks.size()];

        processFutures(tasks, results);

        return Arrays.stream(results).reduce(Integer::sum).orElse(-1);
    }

    private <T> void processFutures(List<IndexTask<T>> tasks, T[] results) {

        System.out.println("processing: " + tasks.size());

        tasks.forEach(fut -> executors.execute(fut));

        List<IndexTask<T>> failed = new ArrayList<>();

        for (IndexTask<T> task : tasks) {

            try {
                T val = task.get(20, TimeUnit.MILLISECONDS);
                results[task.index] = val;
                //System.out.println("Setting: "+val +" at index: "+task.index);

            } catch (Throwable e) {
                task.cancel(true);
                failed.add(task.cloneTask());
            }
        }

        if (!failed.isEmpty()) {
            processFutures(failed, results);
        }
    }

    private static int calculate(int value) {
        try {
            int pause = (int) Math.random() * 10;
            Thread.sleep(pause * 100);

            double rnd = Math.random() * 10;

            if (rnd >= 6.0) {
                //System.out.println(Thread.currentThread().getName() + "- try to acquire LOCK");
                synchronized (LOCK) {
                    //System.out.println("LOCK acquired by: " + Thread.currentThread().getName());
                    Thread.sleep(60 * 60 * 1000);
                }

            }

            return value;
        } catch (Exception ex) {
            //System.out.println(Thread.currentThread().getName() + " thread was interrupted");
            return -1;
        }
    }


    private class IndexTask<V> extends FutureTask<V> {
        private int index;
        private Callable<V> task;

        public IndexTask(int index, Callable<V> task) {
            super(task);
            this.task = task;
            this.index = index;
        }

        private IndexTask cloneTask() {
            return new IndexTask(this.index, this.task);
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


