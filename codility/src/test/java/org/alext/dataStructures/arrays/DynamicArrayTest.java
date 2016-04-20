package org.alext.dataStructures.arrays;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tsumaraa on 19/04/2016.
 */
public class DynamicArrayTest {

    @Test
    public void testRun() throws Exception {
        String input ="2 5\n" +
                "1 0 5\n" +
                "1 1 7\n" +
                "1 0 3\n" +
                "2 1 0\n" +
                "2 1 1";

        DynamicArray dynamicArray =new DynamicArray();

        StringBuilder out = dynamicArray.run(IOUtils.toInputStream(input));
        System.out.print(out);

    }
}