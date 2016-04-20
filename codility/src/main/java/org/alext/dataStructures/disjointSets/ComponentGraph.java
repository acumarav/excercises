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

        Map<Integer, Set<Integer>> fws = new HashMap<Integer, Set<Integer>>();
        //Map<Integer, Set<Integer>> bks = new HashMap<Integer, Set<Integer>>();

        for (int index = 0; index < N; index++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            addRib(a, b, fws);
            //addRib(b, a, bks);
        }

        List<Set<Integer>> sets = new ArrayList<>();
        for(Integer key: fws.keySet()){
            Set<Integer> set = fws.get(key);
            set.add(key);
            sets.add(set);
        }

        sets=flattenGrapth(sets);

        Optional<Integer> min = sets.stream().map(s -> s.size()).reduce(Math::min);
        Optional<Integer> max = sets.stream().map(s -> s.size()).reduce(Math::max);
        return min.get() + " " + max.get();
    }

    private static List<Set<Integer>> flattenGrapth(List<Set<Integer>> sets) {
        long emptySets = 0;
        do {
            for (int i = 0; i < sets.size(); i++) {
                for (int j = i + 1; j < sets.size(); j++) {
                    Set<Integer> donor = sets.get(i);
                    Set<Integer> acceptor = sets.get(j);

                    inner:
                    for (Integer dn : donor) {
                        if (acceptor.contains(dn)) {
                            acceptor.addAll(donor);
                            donor.clear();
                            break inner;
                        }
                    }
                }
            }
            emptySets = sets.stream().filter(s -> s.isEmpty()).count();
            sets = sets.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        }
        while (emptySets > 0);
        return sets;
    }

    private static void addRib(int start, int end, Map<Integer, Set<Integer>> links) {
        if (links.containsKey(start)) {
            links.get(start).add(end);
        } else {
            Set<Integer> map = new HashSet<>();
            map.add(end);
            links.put(start, map);
        }
    }

}
