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

        Map<Integer, Set<Integer>> fws = new HashMap<Integer, Set<Integer>>();
        Map<Integer, Set<Integer>> bks = new HashMap<Integer, Set<Integer>>();

        for (int index = 0; index < N; index++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            addRib(a, b, fws);
            addRib(b, a, bks);
        }

        if(){
        }


        /*Optional<Integer> min = partlyFlattenSet.stream().map(s -> s.size()).reduce(Math::min);
        Optional<Integer> max = partlyFlattenSet.stream().map(s -> s.size()).reduce(Math::max);
        return min.get() + " " + max.get();*/
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
