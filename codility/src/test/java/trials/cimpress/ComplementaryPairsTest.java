package trials.cimpress;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alex on 3/5/2017.
 */
public class ComplementaryPairsTest {


    private ComplementaryPairs complementaryPairs=new ComplementaryPairs();

    @Test
    public void solution() throws Exception {
        int arr[] = {1, 0, -2,    8, 1, 4,    -3, 3, 5};

        int pairs = complementaryPairs.solution(6, arr);

        Assert.assertEquals(7, pairs);

    }

}