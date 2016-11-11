package org.alext.algorithms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 10/5/2016.
 */
public class SubstractFromChunkListTest {

    private List<Chunk> chunks3x10= Arrays.asList(new Chunk(10, "one"), new Chunk(10, "two"), new Chunk(10, "three"));

    private SubstractFromChunkList substracter=new SubstractFromChunkList();


    @Before
    public void setUp(){
    }

    @Test
    public void testCutProperChunks(){

        List<Chunk> out = substracter.substract(chunks3x10, 15, 10);

        Assert.assertEquals(2, out.size());
        Assert.assertEquals(5, out.get(0).getItemsCount());
        Assert.assertEquals("two", out.get(0).getName());
        Assert.assertEquals(5, out.get(1).getItemsCount());
        Assert.assertEquals("three", out.get(1).getName());

    }

    @Test
    public void testCutProperChunksTwo(){

        List<Chunk> out = substracter.substract(chunks3x10, 0, 10);

        Assert.assertEquals(1, out.size());
        Assert.assertEquals(10, out.get(0).getItemsCount());
        Assert.assertEquals("one", out.get(0).getName());
    }

    @Test
    public void testCutProperChunksThree(){

        List<Chunk> out = substracter.substract(chunks3x10, 15, 100);

        Assert.assertEquals(2, out.size());
        Assert.assertEquals(5, out.get(0).getItemsCount());
        Assert.assertEquals("two", out.get(0).getName());
        Assert.assertEquals(5, out.get(1).getItemsCount());
        Assert.assertEquals("three", out.get(1).getName());
    }

    @Test
    public void testCutProperChunksFour(){

        List<Chunk> out = substracter.substract(chunks3x10, 0, 100);

        Assert.assertEquals(3, out.size());
        Assert.assertEquals(10, out.get(0).getItemsCount());
        Assert.assertEquals("one", out.get(0).getName());
        Assert.assertEquals(10, out.get(1).getItemsCount());
        Assert.assertEquals("two", out.get(1).getName());
        Assert.assertEquals(10, out.get(2).getItemsCount());
        Assert.assertEquals("three", out.get(2).getName());
    }

    @Test
    public void testCutProperChunksFive(){

        List<Chunk> out = substracter.substract(chunks3x10, 30, 100);

        Assert.assertEquals(0, out.size());
    }

    @Test
    public void testCutProperChunksSix(){

        List<Chunk> out = substracter.substract(chunks3x10, 29, 100);

        Assert.assertEquals(1, out.size());
        Assert.assertEquals(1, out.get(0).getItemsCount());
        Assert.assertEquals("three", out.get(0).getName());
    }
}
