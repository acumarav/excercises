package org.alext.algorithms;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tsumaraa on 06/06/2016.
 */

public class ReverseStringTest {

    private final String TEST_STRING="lineOne";
    @Test
    public void testReverseRecursively() throws Exception {
        ReverseString rs=new ReverseString();

        String rev = rs.reverseRecursively(TEST_STRING);
        String orig = rs.reverseRecursively(rev);

        assertEquals(TEST_STRING,orig);
        assertEquals("enOenil",rev);
    }

    @Test
    public void testReverseInArray() throws Exception {
        ReverseString rs=new ReverseString();

        String rev = rs.reverseInArray(TEST_STRING);
        String orig = rs.reverseInArray(rev);

        assertEquals(TEST_STRING,orig);
        assertEquals("enOenil",rev);
    }
}