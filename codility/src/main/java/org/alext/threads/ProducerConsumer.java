package org.alext.threads;

import java.util.Vector;

/**
 * Created by tsumaraa on 05/09/2016.
 */
public class ProducerConsumer {


    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc=new ProducerConsumer();
        pc.runProdCons();
    }

    public void runProdCons() throws InterruptedException {
        Vector sharedQueue = new Vector();
        int size = 4;
        Thread prodThread = new Thread(new Producer(sharedQueue, size), "Producer");


        Thread consThread = new Thread(new Consumer(sharedQueue, size), "Consumer");
        prodThread.start();
        consThread.start();

        prodThread.join();

        while(sharedQueue.size()!=0){
            Thread.sleep(50);
        }
        consThread.interrupt();
    }

}

