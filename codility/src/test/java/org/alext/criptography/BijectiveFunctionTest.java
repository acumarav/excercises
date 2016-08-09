package org.alext.criptography;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tsumaraa on 03/08/2016.
 */
public class BijectiveFunctionTest {

    @Test
    public void testBijectiveFunciton(){
        boolean bijective = BijectiveFunction.isBijective("3", "1 2 3");
        assertTrue(bijective);
    }

    @Test
    public void testBijectiveFuncitonTwo(){
        boolean bijective = BijectiveFunction.isBijective("1", "1");
        assertTrue(bijective);
    }

    @Test
    public void testBijectiveFuncitonBad(){
        boolean bijective = BijectiveFunction.isBijective("5", "2 3 4 5 2");
        assertFalse(bijective);
    }

    @Test
    public void testBijectiveFuncitonSix(){
        boolean bijective = BijectiveFunction.isBijective("5", "1 2 3 4 5");
        assertTrue(bijective);
    }


}