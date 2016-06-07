package org.alext.algorithms;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tsumaraa on 07/06/2016.
 */
public class MakeDeadlock {

    private ReentrantLock mylock = new ReentrantLock();

    public void makeDeadlock() throws InterruptedException {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int interruptionCounter=0;
                    mylock.lock();
                    while (true) {
                        System.out.println("Keeping lock: " + mylock.isHeldByCurrentThread() + Thread.currentThread().getName());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            interruptionCounter++;
                            System.out.println("Interruption requested "+interruptionCounter+ " times, will release after 3 requests");
                            if(interruptionCounter==3) {
                                System.out.println("Interruption 3, releasing lock " + Thread.currentThread().getName());
                                mylock.unlock();
                            }
                        }
                    }
                } finally {
                    mylock.unlock();
                }
            }
        });
        th.start();

        Thread.sleep(100);
        int counter =1;
        while(!mylock.tryLock()){
            System.out.println("Cannot aquire lock: "+counter++ +"  "+Thread.currentThread().getName() );
            Thread.sleep(100);

            if(counter % 20==0){
                System.out.println("Interrupting: "+th.getName());
                th.interrupt();
            }
        }

        System.out.println("Lock is mine HURA! "+ mylock.isHeldByCurrentThread());
        mylock.unlock();


    }
}
