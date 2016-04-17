package org.alext.dataStructures;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class HourglassTest {

    @Test
    public void testRun() throws Exception {
        String input = "0 -4 -6 0 -7 -6\n" +
                "-1 -2 -6 -8 -3 -1\n" +
                "-8 -4 -2 -8 -8 -6\n" +
                "-3 -1 -2 -5 -7 -4\n" +
                "-3 -5 -3 -6 -6 -6\n" +
                "-3 -6 0 -8 -6 -7";

        InputStream in = IOUtils.toInputStream(input);
        //expected -19

        int out=Hourglass.run(in);

        assertEquals(-19,out);

    }
}