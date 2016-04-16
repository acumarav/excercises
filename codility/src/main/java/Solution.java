import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 4/16/2016.
 */

class Solution {
    public static int solution(int A, int B, int C, int D) {

        List<Integer> arr = new ArrayList<Integer>();
        arr.add(A);
        arr.add(B);
        arr.add(C);
        arr.add(D);

        int bestShuffle = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int n = 0; n < 4; n++) {
                    for (int k = 0; k < 4; k++) {
                        if (i != j && i != n && i != k && j != n && j != k && n != k) {
                            List<Integer> vals = new ArrayList<Integer>();
                            vals.add(arr.get(i));
                            vals.add(arr.get(j));
                            vals.add(arr.get(n));
                            vals.add(arr.get(k));
                            int result = calcSuffle(vals);
                            if (result > bestShuffle) {
                                bestShuffle = result;
                            }
                        }
                    }
                }

            }
        }

        return bestShuffle;
    }

    public static int calcSuffle(List<Integer> vals) {
        int sum = 0;
        for (int index = 0; index < vals.size() - 1; index++) {
            sum = sum + Math.abs(vals.get(index) - vals.get(index + 1));
        }

        return sum;
    }

    public int solution(List<Integer> numbers) {

        List<List<Integer>> sets = new ArrayList<>(factorial(numbers.size()));
        buildSets(new ArrayList<>(), numbers, sets);

        int bestSuffle=0;

        for (List<Integer> set : sets) {

            int suffle = calcSuffle(set);
            if(suffle>bestSuffle)
                bestSuffle=suffle;
            System.out.println("Shuffle: " + suffle + " for " + set);
        }
        return -1;
    }


    private void buildSets(List<Integer> out, List<Integer> numbers, List<List<Integer>> results) {
        if (numbers.isEmpty()) {
            results.add(out);
        }

        for (int i = 0; i < numbers.size(); i++) {
            List<Integer> rest = new ArrayList<>(numbers);
            Integer num = rest.get(i);
            List<Integer> newOut = new ArrayList<>(out);
            newOut.add(num);
            rest.remove(i);
            buildSets(newOut, rest, results);
        }
    }


    private Map<? extends List<Integer>, ? extends List<Integer>> generateSubsets(List<Integer> key, List<Integer> rest) {
        Map<List<Integer>, List<Integer>> map = new HashMap<>();

        for (int index = 0; index < rest.size(); index++) {
            List<Integer> newKey = new ArrayList<>(key);
            List<Integer> newRest = new ArrayList<>(rest);
            Integer val = newRest.get(index);
            newRest.remove(index);
            newKey.add(val);

            map.put(newKey, newRest);
        }

        return map;
    }

    public int factorial(int fact) {
        int out = 1;
        for (int index = 1; index <= fact; index++) {
            out = out * index;
        }
        return out;
    }

}
