package org.alext.criptography;

import java.util.*;

/**
 * Created by tsumaraa on 03/08/2016.
 */
public class BijectiveFunction {


    public static boolean isBijective(String n, String fn) {

        Integer N = Integer.valueOf(n);
        List<Integer> vals = new ArrayList<Integer>(N);

        for (String part : fn.split(" ")) {
            vals.add(Integer.valueOf(part));
        }

        vals.sort(Integer::compare);

        if (vals.size() != N)
            return false;

        for (int index = 1; index <= N; index++) {
            if (!vals.get(index-1).equals(index)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int[] vals=new int[n];

        for(int index=0;index<n;index++)
        {
            vals[index]=scan.nextInt();
        }

        Arrays.sort(vals);

        for(int index=0;index<n;index++)
        {
            if(vals[index]!=index+1){
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
