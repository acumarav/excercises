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

        pair:
        for (int index = 0; index < N; index++) {
            Integer g = sc.nextInt();
            Integer b = sc.nextInt();

            for (TreeSet<Integer> set : sets) {
                if (set.contains(g) || set.contains(b)) {
                    set.add(g);
                    set.add(b);
                    continue pair;
                }
            }

            TreeSet<Integer> newSet=new TreeSet<>();
            newSet.add(b);
            newSet.add(g);
            sets.add(newSet);
        }

        Optional<Integer> min = sets.stream().map(s -> s.size()).reduce(Math::min);
        Optional<Integer> max = sets.stream().map(s -> s.size()).reduce(Math::max);

        return min.get()+" "+max.get();
    }
}
