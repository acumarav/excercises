package org.alext.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tsumaraa on 09/08/2016.
 */
public class MakingPolygon {
    public int calculateSplits(int[] ribs){
        ArrayList<Integer> parts=new ArrayList<>(ribs.length);

        for(int rib:ribs){
            parts.add(rib);
        }

        int splits=0;
        while (!canBuildPolygon(parts)){
            splits++;

            int max=parts.get(0);
            parts.remove(0);
            int half=(max*2)/3;
            parts.add(half);
            parts.add(max-half);

        }
        return splits;
    }

    private boolean canBuildPolygon(ArrayList<Integer> parts) {

        Collections.sort(parts);
        Collections.reverse(parts);

        int sum=0;
        for(int index=1;index<parts.size();index++) {
            sum += parts.get(index);
        }

        return parts.size()>2 && sum>parts.get(0);


    }
}
