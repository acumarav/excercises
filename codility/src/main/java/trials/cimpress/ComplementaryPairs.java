package trials.cimpress;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by alex on 3/5/2017.
 */
public class ComplementaryPairs {

    public int solution(int K, int[] A) {
        if (A == null || A.length == 0) {
            throw new IllegalArgumentException();
        }

        int pairCounter = 0;

        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                int sum = A[i] + A[j];
                if (sum == K) {
                    if (i == j) {
                        pairCounter = pairCounter + 1;
                    } else {
                        pairCounter = pairCounter + 2;
                    }
                }
            }
        }
        return pairCounter;
    }
}
