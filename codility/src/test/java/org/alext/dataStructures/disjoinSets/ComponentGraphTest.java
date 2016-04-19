package org.alext.dataStructures.disjoinSets;

import org.alext.dataStructures.disjointSets.ComponentGraph;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by alex on 4/19/2016.
 */
public class ComponentGraphTest {

    @Test
    public void testRun(){
        String input="5\n" +
                "1 6 \n" +
                "2 7\n" +
                "3 8\n" +
                "4 9\n" +
                "2 6";
        ComponentGraph graph=new ComponentGraph();

        String out = graph.run(IOUtils.toInputStream(input));

        assertEquals("2 4",out);
    }
}
