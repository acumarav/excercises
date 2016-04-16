import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/16/2016.
 */
public class Solution2 {
    public int solution(int[] A) {
        int result = -1;

        if (A == null || A.length == 0) {
            return result;
        }

        int sum = 0;
        for (int index = 0; index < A.length; index++) {
            sum += A[index];
        }
        double average = sum / (0.0 + A.length);

        List<Double> divs = new ArrayList<Double>(A.length);


        for (int index = 0; index < A.length; index++) {
            divs.add(Math.abs(A[index] - average));
        }

        Double max = divs.stream().reduce((a, b) -> Math.max(a, b)).get();

        return divs.indexOf(max);
    }
}
