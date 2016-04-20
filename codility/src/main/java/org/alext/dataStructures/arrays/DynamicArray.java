package org.alext.dataStructures.arrays;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tsumaraa on 19/04/2016.
 */
public class DynamicArray {
    public static StringBuilder run(InputStream in) {
        Scanner sc = new Scanner(in);
        int N = sc.nextInt();
        int Q = sc.nextInt();

        int lastAns = 0;
        List<List<Integer>> seqList=new ArrayList<>(N);
        for(int index=0;index<N;index++){
            seqList.add(new ArrayList<>());
        }

        StringBuilder sb = new StringBuilder();

        for (int index = 0; index < Q; index++) {
            int q=sc.nextInt();
            int x=sc.nextInt();
            int y=sc.nextInt();

            if(q==1){
                int ind=(x^lastAns) % N;
                List<Integer> seq=seqList.get(ind);
                seq.add(y);
            }
            if(q==2){
                int ind=(x^lastAns) % N;
                List<Integer> seq=seqList.get(ind);
                int seqIndex=y % seq.size();
                lastAns = seq.get(seqIndex);
                sb.append(lastAns+"\n");
            }
        }

        return sb;
    }
}
