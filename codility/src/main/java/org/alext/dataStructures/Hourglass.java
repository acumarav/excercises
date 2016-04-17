package org.alext.dataStructures;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by alex on 4/17/2016.
 */
public class Hourglass {

    public static int run(InputStream in) {
        Scanner sc = new Scanner(in);
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++) {
                arr[j][i] = sc.nextInt();
            }

        int maxVal=Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                int val=arr[i][j]+arr[i+1][j]+arr[i+2][j]
                        +arr[i+1][j+1]
                        +arr[i][j+2] +arr[i+1][j+2]+arr[i+2][j+2];

                maxVal=Math.max(val,maxVal);
            }

        System.out.println(maxVal);

        return maxVal;
    }
}
