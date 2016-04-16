package org.alext.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by alex on 4/16/2016.
 */
public class BinaryGap {

    public int solution(int N) {

        String s = Integer.toBinaryString(N);
        System.out.println(s);

        s = trim(s, '0');
        List<String> parts = split(s, '1');
        Optional<Integer> maxLength = parts.stream().map(p -> p.length()).reduce((a, b) -> Math.max(a, b));

        if (maxLength.isPresent())
            return maxLength.get();
        return 0;
    }

    List<String> split(String s, char ch) {
        List<String> parts = new ArrayList<>();
        int startIndex = s.indexOf(ch);

        do {
            int nextIndex = s.indexOf(ch, startIndex+1);
            if (nextIndex > 0) {
                String part = s.substring(startIndex + 1, nextIndex);
                if (part.length() > 0) {
                    parts.add(part);
                }
            }
            startIndex = nextIndex;
        } while (startIndex != -1);
        return parts;
    }

    private String trim(String s, char c) {
        int last = s.lastIndexOf('1');

        if (last != -1) {
            return s.substring(0, last + 1);
        }
        return s;
    }
}
