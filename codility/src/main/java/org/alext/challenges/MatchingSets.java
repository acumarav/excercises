package org.alext.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tsumaraa on 10/08/2016.
 */
public class MatchingSets {

    public static void removeCommonElements(List<Integer> la, List<Integer> lb) {
        Integer[] a = la.toArray(new Integer[la.size()]);


        for (int index = 0; index < a.length; index++) {
            Integer val = a[index];
            if (la.contains(val) && lb.contains(val)) {
                la.remove(val);
                lb.remove(val);
            }
        }
    }

    public static List<Integer> toList(int[] arr) {
        List<Integer> lst = new ArrayList<>(arr.length);
        for (int index = 0; index < arr.length; index++)
            lst.add(arr[index]);

        return lst;
    }

    public static int calMutations(int[] inA, int[] inB) {

        List<Integer> lx = toList(inA);
        List<Integer> ly = toList(inB);

        removeCommonElements(lx, ly);

        Integer[] X = lx.toArray(new Integer[lx.size()]);
        Integer[] Y = ly.toArray(new Integer[ly.size()]);

        Arrays.sort(Y);

        int cnt = 0;

        for (int i = 0, j = X.length - 1; i < j; ) {
            //if(X[i]<Y[i])
        }
//4,3,0 -can do
//4,0,3 -cannot do
//4,1,2

//1,2
//3,0 +
//0,3

        return -1;

    }

    public static int calMutations2(int[] inA, int[] inB) {

        List<Integer> la = toList(inA);
        List<Integer> lb = toList(inB);

        removeCommonElements(la, lb);

        Integer[] A = la.toArray(new Integer[la.size()]);
        Integer[] B = lb.toArray(new Integer[lb.size()]);

        int delPos = 0;
        int delNeg = 0;

        Arrays.sort(A);
        Arrays.sort(B);


        for (int index = 0; index < A.length; index++) {
            int delta = A[index] - B[index];

            if (delta > 0) {
                delPos += Math.abs(delta);
            } else {
                delNeg += Math.abs(delta);
            }
        }

        if (delPos != delNeg) {
            return -1;
        }
        return delPos;
    }
}
