import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tsumaraa on 18/04/2016.
 */
public class Solution3Test {

    private Solution3 s=new Solution3();

    @Test
    public void testSolution() throws Exception {
        int[] line1=new int[]{1,1,1,1,0,0,0,0,1,1};
        int[] line2=new int[]{1,1,1,1,0,1,0,0,1,1};
        assertEquals(5,s.solution(line2));
        assertEquals(5,s.solution(line1));
    }
}