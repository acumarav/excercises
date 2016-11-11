package org.alext.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10/5/2016.
 */
public class SubstractFromChunkList {

    public List<Chunk> substract(List<Chunk> chunks, int from, int length) {

        List<Chunk> out=new ArrayList<>();

        for(Chunk chunk:chunks){
            if(chunk.getItemsCount()<from+1){
                from-=chunk.getItemsCount();
                continue;
            }

            if(length>0){
                int remainsInChunk = chunk.getItemsCount() - from;
                if(remainsInChunk>=length) {
                    Chunk ch = new Chunk(length, chunk.getName());
                    length-=ch.getItemsCount();
                    out.add(ch);
                }
                else {
                    Chunk ch = new Chunk(remainsInChunk, chunk.getName());
                    length-=ch.getItemsCount();
                    out.add(ch);
                }

            }
        }

        return out;
    }


}
