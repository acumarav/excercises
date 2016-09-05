package org.alext.threads;

import java.util.Vector;

public class Producer implements Runnable{
    private final Vector sharedQueue;
    private final int Size;

    public Producer(Vector sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        Size = size;
    }

    @Override
    public void run() {
        for(int i=0;i<=20;i++){
            System.out.println("Produced: "+i);

            try{
                produce(i);
            }catch (InterruptedException ex){
                System.out.println("Producer Interrupted..");
            }
        }
    }

    private void produce(int i) throws InterruptedException {
        while (sharedQueue.size()==Size){
            synchronized (sharedQueue){
                System.out.println("Queue is full "+ Thread.currentThread().getName() + "is waiting, size: "+sharedQueue.size());
                sharedQueue.wait();
            }
        }

        synchronized (sharedQueue){
            sharedQueue.add(i);
            sharedQueue.notifyAll();
        }
    }
}
