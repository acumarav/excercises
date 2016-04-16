package org.alext.training;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alex on 4/16/2016.
 */
public class BinaryGapTest {

    @Test
    public void testSolution() throws Exception {
        BinaryGap gap=new BinaryGap();

        assertEquals(2,gap.solution(9));
        assertEquals(5,gap.solution(1041));
        assertEquals(0,gap.solution(15));

    }
}