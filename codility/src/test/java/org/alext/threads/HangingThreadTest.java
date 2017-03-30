package org.alext.threads;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by alext on 3/30/2017.
 */
public class HangingThreadTest {

    private HangingThread command;

    @Before
    public void setUp() throws Exception {
        command = new HangingThread();
    }

    @Test
    public void test10(){
        int result = command.runFutureCalculations(10);

        Assert.assertEquals(45,result);
    }


    @Test
    public void test20(){
        int result = command.runFutureCalculations(20);

        Assert.assertEquals(190,result);
    }


    @Test
    public void test100(){
        int result = command.runFutureCalculations(100);

        Assert.assertEquals(4950,result);
    }

    @Test
    public void test40PoolRecovery(){
        int result = command.runFutureCalculations(40);
        System.out.println("Result 1: "+result);

        int result2 = command.runFutureCalculations(40);        ;

        Assert.assertEquals(result,result2);

        Assert.assertEquals(780,result);
        Assert.assertEquals(780,result2);
    }

    @Test
    public void test1000poolRecovery(){
        int result = command.runFutureCalculations(100);
        System.out.println("Result 1: "+result);

        int result2 = command.runFutureCalculations(100);
        System.out.println("Result 2: "+result2);


        Assert.assertEquals(result,result2);
        Assert.assertEquals(4950,result);
        Assert.assertEquals(4950,result2);
    }

}