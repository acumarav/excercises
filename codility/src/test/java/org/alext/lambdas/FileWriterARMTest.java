package org.alext.lambdas;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by tsumaraa on 09/09/2016.
 */
public class FileWriterARMTest {

    @Test
    public void run() throws IOException {
        File tempFile = File.createTempFile("alext", "tmp");
        FileWriterARM.run(tempFile);
    }

    @Test
    public void testUseFunction() throws IOException {
        File tempFile = File.createTempFile("alext", "tmp");
        FileWriterARM.use(tempFile, writerEAM->writerEAM.writeStuff("hello world!"));
    }

}