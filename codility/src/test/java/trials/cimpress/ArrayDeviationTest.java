package trials.cimpress;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

/**
 * Created by alex on 3/5/2017.
 */
public class ArrayDeviationTest {

    private ArrayDeviation arrayDeviation=new ArrayDeviation();
    @Test
    public void solution() throws Exception {
        int A[]={9,4,-3,-10};

        int result = arrayDeviation.solution(A);

        Assert.assertEquals(3,result);
    }

    @Test
    public void solutionEmpty() throws Exception {
        int A[]={};

        int result = arrayDeviation.solution(A);

        Assert.assertEquals(-1,result);
    }

}