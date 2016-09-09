package org.alext.lambdas;

import org.junit.Test;

/**
 * Created by tsumaraa on 09/09/2016.
 */
public class SeaPlaneTest {
    @Test
    public void cruise() throws Exception {
        SeaPlane seaPlane = new SeaPlane();

        seaPlane.takeOff();

        seaPlane.turn();

        seaPlane.cruise();

        seaPlane.land();

    }

}