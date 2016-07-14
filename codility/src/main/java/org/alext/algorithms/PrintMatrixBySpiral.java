package org.alext.algorithms;

/**
 * Created by tsumaraa on 14/07/2016.
 */
public class PrintMatrixBySpiral {

    public String inlineMatrix(char[][] matrix) {

        int width = matrix[0].length;
        int height = matrix.length;

        int l = 0;
        int r = width-1;
        int t = 0;
        int b = height-1;
        StringBuilder sb = new StringBuilder();

        while (l <= r && t <= b) {
            sliceFrame(sb, matrix, l++, t++, r--, b--);
        }

        return sb.toString();
    }

    private void sliceFrame(StringBuilder sb, char[][] matrix, int lf, int tp, int rt, int bt) {
        System.out.println(lf + " - " + tp + " - " + rt + " - " + bt);

        for(int index=lf;index<=rt;index++){
            sb.append(matrix[tp][index]);
        }

        for(int index=tp+1;index<=bt;index++){
            sb.append(matrix[index][rt]);
        }
        for(int index=rt-1;index>=lf;index--){
            sb.append(matrix[bt][index]);
        }

        for(int index=bt-1;index>tp;index--){
            sb.append(matrix[index][lf]);
        }

    }

}
