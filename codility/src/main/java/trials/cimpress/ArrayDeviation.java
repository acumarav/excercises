package trials.cimpress;

/**
 * Created by alex on 3/5/2017.
 */
public class ArrayDeviation {

    public int solution(int[] A) {

        if (A == null || A.length == 0) {
            return -1;
        }

        long acc = 0;

        for (int index = 0; index < A.length; index++) {
            acc += A[index];
        }

        double median = (double) acc / ((double) A.length);

        double[] normalized = new double[A.length];

        for (int index = 0; index < A.length; index++) {
            normalized[index] = Math.abs(A[index] - median);
        }

        double maxVal = Double.MIN_VALUE;
        for (int index = 0; index < A.length; index++) {
            maxVal = Math.max(maxVal, normalized[index]);
        }

        for (int index = 0; index < A.length; index++) {
            if (normalized[index] == maxVal) {
                return index;
            }
        }

        return -1;

    }
}
