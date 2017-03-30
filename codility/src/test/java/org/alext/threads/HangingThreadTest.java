package org.alext.threads;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        int result = command.runCalculations(10);

        Assert.assertEquals(45,result);
    }


    @Test
    public void test20(){
        int result = command.runCalculations(20);

        Assert.assertEquals(190,result);
    }


}