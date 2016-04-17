package org.alext.linkedList;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }


    @Test
    public void testLinkedList(){
        LinkedList<Integer> list=new LinkedList();

        list.add(1);
        list.add(5);
        list.add(7);

        assertEquals(3, list.size());

        assertEquals((Integer)1, list.get());
        assertEquals((Integer)5, list.get());
        assertEquals((Integer)7, list.get());

        assertEquals(0, list.size());
    }

    @Test
    public void testListReverse(){
        LinkedList<Integer> list=new LinkedList();

        list.add(1);
        list.add(5);
        list.add(7);
        list.add(11);

        assertEquals(4, list.size());
        list.reverse();

        assertEquals((Integer)11, list.get());
        assertEquals((Integer)7, list.get());
        assertEquals((Integer)5, list.get());
        assertEquals((Integer)1, list.get());

        assertEquals(0, list.size());

    }
}