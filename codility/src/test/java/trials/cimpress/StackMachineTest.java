package trials.cimpress;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alex on 3/5/2017.
 */
public class StackMachineTest {

    private StackMachine stackMachine=new StackMachine();

    @Test
    public void solution() throws Exception {
        int result = stackMachine.solution("13+62*7+*");

        Assert.assertEquals(76,result);
    }

    @Test
    public void auxTest(){
        Integer a=Integer.MAX_VALUE;
        Integer b=20*a;
        //Math.multiplyExact(a,20);
        Math.addExact(a,a);
        Assert.assertTrue(true);

    }

    @Test
    public void testOverflows(){
        Assert.assertFalse(StackMachine.check12bitOverflow(0));
        Assert.assertFalse(StackMachine.check12bitOverflow(0b111111111111));
        Assert.assertFalse(StackMachine.check12bitOverflow(0b11111111111));

        Assert.assertTrue(StackMachine.check12bitOverflow(0b1111111111110));
        Assert.assertTrue(StackMachine.check12bitOverflow(0b1111111111111));
    }



}