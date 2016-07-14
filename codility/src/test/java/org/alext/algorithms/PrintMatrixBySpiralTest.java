package org.alext.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tsumaraa on 14/07/2016.
 */
public class PrintMatrixBySpiralTest {

    private char[][] matrixOne;
    private char[][] matrixTwo;
    private PrintMatrixBySpiral spiral=new PrintMatrixBySpiral();

    @Before
    public void setUp(){
        matrixOne=new char[3][3];
        matrixOne[0][0]='o';
        matrixOne[0][1]='n';
        matrixOne[0][2]='e';

        matrixOne[1][0]='e';
        matrixOne[1][1]='d';
        matrixOne[1][2]='s';

        matrixOne[2][0]='v';
        matrixOne[2][1]='l';
        matrixOne[2][2]='o';
    }

    @Test
    public void testInlineMatrix() throws Exception {
        String line = spiral.inlineMatrix(matrixOne);

        assertEquals("onesolved",line);
    }

}