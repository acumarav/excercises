package org.alext.isoParser;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.MemoryDataSourceImpl;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.CroppedTrack;
import com.googlecode.mp4parser.util.Matrix;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.alext.isoParser.MpegScissors.getFirstSounTrack;
import static org.alext.isoParser.MpegScissors.getFirstVideTrack;

/**
 * Created by tsumaraa on 13/09/2016.
 */
public class MpegScissorsTest {

    private MemoryDataSourceImpl sampleMp4mds;
    private MpegScissors scissors=new MpegScissors();

    @Before
    public void setUp() throws IOException {
        InputStream sampleMp4is = MpegScissorsTest.class.getResourceAsStream("/video/sample_mpeg4.mp4");
        byte[] sampleBytes = IOUtils.toByteArray(sampleMp4is);
        sampleMp4mds=new MemoryDataSourceImpl(sampleBytes);
    }

    @Test
    public void testListSamplesType() throws IOException {
        Movie mov = MovieCreator.build(sampleMp4mds);

        int index=0;
        for(Track tk:mov.getTracks()){
            printTrackInfo(index, tk);
            index++;
        }
        Track vtr = getFirstVideTrack(mov);
        printTrackInfo(-1, vtr);
        System.out.println("vide track samples info:");
        printTrackSamplesInfo(vtr);
    }

    private void printTrackSamplesInfo(Track vtr) {
        int index;
        index=0;
        for(Sample sm: vtr.getSamples()){
            System.out.println(index+ " Size: "+sm.getSize());
            System.out.println(index+ " Name: "+sm.getClass().getName());
            index++;
        }
        System.out.println("Sync samples: " );
        Arrays.stream(vtr.getSyncSamples()).mapToObj(v->""+v+" ").forEach(System.out::print);
    }

    private void printTrackInfo(int index, Track tk) {
        System.out.println(index+ " Name: "+ tk.getName());
        System.out.println(index+ " Handler: "+ tk.getHandler());
        System.out.println(index+ " Type: "+ tk.getClass().getName());
    }

    @Test
    public void splitIntoParts() throws Exception {
        Movie mov = MovieCreator.build(sampleMp4mds);

        Movie outMovie=new Movie();

        for(Track track: mov.getTracks())
        {
            long[] durations = track.getSampleDurations();
            long totalDuration = Arrays.stream(durations).reduce((a, b) -> a + b).getAsLong();
            CroppedTrack ctr=new CroppedTrack(track,0,50);
            outMovie.addTrack(ctr);
        }

        new DefaultMp4Builder().build(outMovie).writeContainer(new FileOutputStream("C:\\del\\output.mp4").getChannel());
    }

    @Test
    public void testLengthInSeconds() throws IOException {
        IsoFile isoFile=new IsoFile(sampleMp4mds);
        double lengthInSeconds = (double) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();

        System.out.println("Video length: "+lengthInSeconds);
    }

    @Test
    public void testISOFileContainer() throws IOException {
        int level=1;
        IsoFile isoFile=new IsoFile(sampleMp4mds);
        isoFile.getBoxes().forEach(b->printBox(b, level));
    }
    private static void printBox(Box box, int level){
        char tab='\t';
        StringBuilder sb=new StringBuilder();
        for (int index=0;index<level;index++){
            sb.append(tab);
        }
        System.out.printf("Level:%d\t%sBoxType: %s Sz: %8d  Offs:%8d \n",level,sb.toString(),box.getType(),box.getSize(),box.getOffset());

        if(Container.class.isInstance(box)){
            Container container = Container.class.cast(box);
            container.getBoxes().forEach(b->printBox(b,level+1));
        }
    }

}