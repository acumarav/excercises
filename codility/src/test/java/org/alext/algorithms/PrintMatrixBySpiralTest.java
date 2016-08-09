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

        matrixTwo=new char[4][4];

        matrixTwo[0][0]='h';
        matrixTwo[0][1]='o';
        matrixTwo[0][2]='w';
        matrixTwo[0][3]=' ';

        matrixTwo[1][0]=' ';
        matrixTwo[1][1]='t';
        matrixTwo[1][2]='h';
        matrixTwo[1][3]='g';

        matrixTwo[2][0]='s';
        matrixTwo[2][1]='y';
        matrixTwo[2][2]='e';
        matrixTwo[2][3]='o';

        matrixTwo[3][0]='i';
        matrixTwo[3][1]=' ';
        matrixTwo[3][2]='d';
        matrixTwo[3][3]='o';
    }

    @Test
    public void testInlineMatrix() throws Exception {
        String line = spiral.inlineMatrix(matrixOne);

        assertEquals("onesolved",line);
    }

    @Test
    public void testInlineMatrixTwo() throws Exception {

        String lineTwo = spiral.inlineMatrix(matrixTwo);
        assertEquals("how good is they",lineTwo);
    }

}