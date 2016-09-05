package org.alext.threads;

import org.junit.Test;

/**
 * Created by tsumaraa on 05/09/2016.
 */
public class ProducerConsumerTest {



    @Test
    public void runProdCons() throws Exception {
        ProducerConsumer pc=new ProducerConsumer();
        pc.runProdCons();
    }

}