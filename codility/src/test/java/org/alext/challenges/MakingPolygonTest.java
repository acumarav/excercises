package org.alext.challenges;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tsumaraa on 09/08/2016.
 */
public class MakingPolygonTest {

    private MakingPolygon polygon=new MakingPolygon();



    @Test
    public void calculateSplits() throws Exception {
        assertEquals(1,polygon.calculateSplits(new int[]{1,2,3}));
        assertEquals(0,polygon.calculateSplits(new int[]{3,4,5}));
        assertEquals(2,polygon.calculateSplits(new int[]{10,10}));
        assertEquals(0,polygon.calculateSplits(new int[]{10,10,10}));
        assertEquals(2,polygon.calculateSplits(new int[]{10}));
    }

}