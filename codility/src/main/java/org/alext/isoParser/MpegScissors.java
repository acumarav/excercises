package org.alext.isoParser;

import com.coremedia.iso.IsoFile;
import com.googlecode.mp4parser.MemoryDataSourceImpl;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * Created by tsumaraa on 13/09/2016.
 */
public class MpegScissors {

    public  OutputStream[]  splitIntoParts(MemoryDataSourceImpl input, int numberOfParts){


        //MovieCreator.build()
        return null;
    }

    public static Track getFirstVideTrack(Movie mov){
        for(Track tr:mov.getTracks()){
            if("vide".equals( tr.getHandler()))
                return tr;
        }
        return null;
    }
    public static Track getFirstSounTrack(Movie mov){
        for(Track tr:mov.getTracks()){
            if("soun".equals( tr.getHandler()))
                return tr;
        }
        return null;
    }
}
