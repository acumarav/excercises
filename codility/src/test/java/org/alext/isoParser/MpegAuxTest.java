package org.alext.isoParser;

import com.googlecode.mp4parser.MemoryDataSourceImpl;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by tsumaraa on 23/09/2016.
 */
public class MpegAuxTest {

    private MemoryDataSourceImpl sampleMp4mds;

    @Before
    public void setUp() throws IOException {
        InputStream sampleMp4is = MpegAuxTest.class.getResourceAsStream("/video/sample_mpeg4.mp4");
        byte[] sampleBytes = IOUtils.toByteArray(sampleMp4is);
        sampleMp4mds=new MemoryDataSourceImpl(sampleBytes);
    }

    @Test
    public void runPlayer() throws IOException, URISyntaxException, InterruptedException {
        URL mpegUrl = MpegAuxTest.class.getResource("/video/sample_mpeg4.mp4");
        String player = System.getenv("ProgramFiles") + "\\Windows Media Player\\wmplayer.exe";

        File f=new File(mpegUrl.toURI());

        String command = (player+" " + f.getPath());
        Process proc = Runtime.getRuntime().exec(command);
        Thread.sleep(3500);
        proc.destroy();
    }

}
