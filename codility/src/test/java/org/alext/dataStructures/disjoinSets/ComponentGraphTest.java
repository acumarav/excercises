package org.alext.dataStructures.disjoinSets;

import org.alext.dataStructures.disjointSets.ComponentGraph;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by alex on 4/19/2016.
 */
public class ComponentGraphTest {

    @Test
    public void testRun() {
        long millisStart = System.currentTimeMillis();
        String input = "5\n" +
                "1 6 \n" +
                "2 7\n" +
                "3 8\n" +
                "4 9\n" +
                "2 6";
        ComponentGraph graph = new ComponentGraph();

        String out = graph.run(IOUtils.toInputStream(input));

        assertEquals("2 4", out);

        System.out.println("Done in(ms): "+(System.currentTimeMillis()-millisStart));
    }

    @Test
    public void testCase25() {
        long millisStart = System.currentTimeMillis();


        InputStream in = this.getClass().getResourceAsStream("/org.alext/dataStructures/disjoinSets/case25.txt");
        ComponentGraph graph = new ComponentGraph();
        String out = graph.run(in);

        Assert.assertEquals("2 1072", out);

        System.out.println("Done in(ms): "+(System.currentTimeMillis()-millisStart));
    }
}
