package org.alext.challenges;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tsumaraa on 10/08/2016.
 */
public class MatchingSetsTest {

    private  MatchingSets matchingSets=new MatchingSets();

    @Test
    public void testCalMutationsPossible() throws Exception {
        int mutations = matchingSets.calMutations(new int[]{1, 2, 3}, new int[]{-1, 4, 3});
        assertEquals(2,mutations);
    }

    @Test
    public void testCalMutationsPossibleOrder() throws Exception {
        int mutations = matchingSets.calMutations(new int[]{0,3}, new int[]{1,2});
        assertEquals(-1,mutations);
    }

   /* @Test
    public void testCalMutationsPossibleOrder2() throws Exception {
        int mutations = matchingSets.calMutations(new int[]{1, 2, 3, 4}, new int[]{-1,-1,9,3});
        assertEquals(5,mutations);
    }*/

    @Test
    public void testCalMutationsNotPossible() throws Exception {
        int mutations = matchingSets.calMutations(new int[]{1, 2, 3}, new int[]{2, 4, 3});
        assertEquals(-1,mutations);
    }

}