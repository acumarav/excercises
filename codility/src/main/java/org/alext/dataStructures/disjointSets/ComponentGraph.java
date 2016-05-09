package org.alext.dataStructures.disjointSets;

import java.io.InputStream;
import java.util.*;

/**
 * Created by alex on 4/19/2016.
 */
public class ComponentGraph {

    public static String run(InputStream in) {
        Scanner sc = new Scanner(in);
        int N = sc.nextInt();
        List<TreeSet<Integer>> sets = new ArrayList<>();

        for (int index = 0; index < N; index++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            addRib(a, b, sets);
        }

        Optional<Integer> min = sets.stream().map(s -> s.size()).reduce(Math::min);
        Optional<Integer> max = sets.stream().map(s -> s.size()).reduce(Math::max);
        return min.get() + " " + max.get();
    }

    private static void addRib(Integer a, Integer b, List<TreeSet<Integer>> sets) {
        TreeSet<Integer> firstHolder = null;
        List<TreeSet> toRemove = new ArrayList<>();

        for (int index = 0; index < sets.size(); index++) {
            TreeSet<Integer> set = sets.get(index);
            if (set.contains(a) || set.contains(b)) {
                set.add(a);
                set.add(b);

                if (firstHolder != null) {
                    firstHolder.addAll(set);
                    set.clear();
                    toRemove.add(set);
                } else {
                    firstHolder = set;
                }
            }
        }

        if (firstHolder == null) {
            TreeSet<Integer> newSet = new TreeSet<>();
            newSet.add(a);
            newSet.add(b);
            sets.add(newSet);
        }

        sets.removeAll(toRemove);
    }

}
