package org.alext.isoParser;

import com.googlecode.mp4parser.MemoryDataSourceImpl;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
    public void runPlayer() throws IOException {
        //Runtime.getRuntime().exec("c:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe");
        //"c:\Program Files (x86)\Windows Media Player\wmplayer.exe"  c:\projects\java\excercises\codility\src\test\resources\video\sample_mpeg4.mp4-muxed.mp4d
        URL mpegUrl = MpegAuxTest.class.getResource("/video/sample_mpeg4.mp4");
        String video = mpegUrl.getPath();
        String player = System.getenv("ProgramFiles") + "\\Windows Media Player\\wmplayer.exe";

        String command = (player + video).replace("/C:", " C:");
        Runtime.getRuntime().exec(command);
        //Runtime.getRuntime().exec(new String[]{player,video});
    }

}
