package org.alext.dataStructures.disjointSets;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by alex on 4/19/2016.
 */
public class ComponentGraph {

    public static String run(InputStream in) {

        Scanner sc = new Scanner(in);
        int N = sc.nextInt();

        List<TreeSet<Integer>> sets = new ArrayList<>();


        for (int index = 0; index < N; index++) {
            TreeSet<Integer> newSet = new TreeSet<>();
            newSet.add(sc.nextInt());
            newSet.add(sc.nextInt());
            sets.add(newSet);
        }

        long emptySets = 0;
        do {
            for (int i = 0; i < sets.size(); i++) {
                for (int j = i + 1; j < sets.size(); j++) {
                    TreeSet<Integer> donor = sets.get(i);
                    TreeSet<Integer> acceptor = sets.get(j);

                    for (Integer dn : donor) {
                        if (acceptor.contains(dn)) {
                            acceptor.addAll(donor);
                            donor.clear();
                            break;
                        }
                    }
                }
            }
            emptySets = sets.stream().filter(s -> s.isEmpty()).count();
            sets = sets.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        }
        while (emptySets > 0);


        Optional<Integer> min = sets.stream().map(s -> s.size()).reduce(Math::min);
        Optional<Integer> max = sets.stream().map(s -> s.size()).reduce(Math::max);

        return min.get() + " " + max.get();
    }
}
