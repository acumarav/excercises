package org.alext.lambdas;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tsumaraa on 09/09/2016.
 */
public class WatchFilesTest {
    @Test
    public void watchCFiles() throws Exception {
        WatchFiles watchFiles=new WatchFiles();
        watchFiles.watchCFiles();

    }

}