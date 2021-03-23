package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 0:37
 *   @Description :
 *
 */

import java.util.Scanner;

/**
 * 牛牛和 15 个朋友来玩打土豪分田地的游戏，牛牛决定让你来分田地，地主的田地可以看成是一个矩形，每 个位置有一个价值。分割田地的方法是横竖各切三刀，分成 16 份，作为领导干部，
 * 牛牛总是会选择其中总 价值最小的一份田地， 作为牛牛最好的朋友，你希望牛牛取得的田地的价值和尽可能大，你知道这个值最大 可以是多少吗？
 */
public class SplitFields {

    public static int minimax(int[][] matrix){
        if(matrix == null || matrix.length < 4 || matrix[0].length < 4){
            return 0;
        }
        int[][] prefix = getPrefixSumMatrix(matrix);
        int m = matrix.length, n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for(int c1 = 0; c1 < n - 3; c1 ++){
            for(int c2 = c1 + 1; c2 < n - 2; c2 ++){
                for(int c3 = c2 + 1; c3 < n - 1; c3 ++){
                    res = Math.max(res, getBestDecision(prefix, c1, c2, c3));
                }
            }
        }
        return res;
    }

    // 2D prefix sum
    public static int[][] getPrefixSumMatrix(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        int[][] prefix = new int[m][n];
        prefix[0][0] = matrix[0][0];
        int i, j;
        for(i = 1; i < m; i ++){
            prefix[i][0] = prefix[i-1][0] + matrix[i][0];
        }
        for(i = 1; i < m; i ++){
            prefix[0][i] = prefix[0][i-1] + matrix[0][i];
        }
        for(i = 1; i < m; i ++){
            for(j = 1; j < n; j ++){
                prefix[i][j] = prefix[i][j-1] + prefix[i-1][j] - prefix[i-1][j-1] + matrix[i][j];
            }
        }
        return prefix;
    }

    public static int getBestDecision(int[][] prefix, int c1, int c2, int c3){
        int[] up = getUpSplitArray(prefix, c1, c2, c3);
        int[] down = getDownSplitArray(prefix, c1, c2 ,c3);
        int res = Integer.MIN_VALUE;
        for(int i = 1; i < prefix.length - 2; i ++){
            res = Math.max(res, Math.min(up[i], down[i+1]));
        }
        return res;
    }

   public static int[] getUpSplitArray(int[][] prefix, int c1, int c2, int c3){
        int size = prefix.length;
        int[] up = new int[size];
        int split = 0;
        up[1] = Math.min(value(prefix,c1, c2, c3, 0, 0),value(prefix, c1, c2, c3, 1, 1));
        for(int i = 2; i < size; i ++){
            int minMax = towSubMatrixMin(prefix, c1, c2, c3, 0, split, i);
            while(split < i){
                if(split == i - 1){
                    break;
                }
                int moved = towSubMatrixMin(prefix, c1, c2, c3, 0, split + 1, i);
                if(moved < minMax){
                    break;
                } else{
                    minMax = moved;
                    split ++;
                }
            }
            up[i] = minMax;
        }
        return up;
   }

   public static int[] getDownSplitArray(int[][] prefix, int c1, int c2, int c3){
       int size = prefix.length;
       int[] down = new int[size];
       int split = size - 1;
       down[size - 2] = Math.min(value(prefix, c1, c2, c3, size - 2, size - 2),
               value(prefix, c1, c2, c3, size - 1, size - 1));
       for (int i = size - 3; i >= 0; i--) {
           int minMax = towSubMatrixMin(prefix, c1, c2, c3, i, split - 1, size - 1);
           while (split > i) {
               if (split == i + 1) {
                   break;
               }
               int moved = towSubMatrixMin(prefix, c1, c2, c3, i, split - 2, size - 1);
               if (moved < minMax) {
                   break;
               } else {
                   minMax = moved;
                   split--;
               }
           }
           down[i] = minMax;
       }
       return down;
   }

   public static int towSubMatrixMin(int[][] prefix, int c1, int c2, int c3, int i, int split, int j){
        return Math.min(value(prefix, c1, c2, c3, i, split), value(prefix, c1, c2, c3, split + 1, j));
   }

   public static int value(int[][] prefix, int c1, int c2, int c3, int prow, int crow){
        int value1 = area(prefix, prow, 0, crow, c1);
        int value2 = area(prefix, prow, c1 + 1, crow, c2);
        int value3 = area(prefix, prow, c2 + 1, crow, c3);
        int value4 = area(prefix, prow, c3 + 1, crow, prefix[0].length - 1);
        return Math.min(Math.min(value1, value2), Math.min(value3, value4));
   }

   public static int area(int[][] prefix, int i1, int j1, int i2 , int j2){
        int all = prefix[i2][j2];
        int left = j1 > 0 ? prefix[i2][j1-1] : 0;
        int up = i1 > 0 ? prefix[i1-1][j2] : 0;
        int dup = (i1 > 0 && j1 > 0) ? prefix[i1-1][j1-1] : 0;
        return all - left - up + dup;
   }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] matrix = new int[n][m];
            for (int i = 0; i < n; i++) {
                char[] chas = in.next().toCharArray();
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = chas[j] - '0';
                }
            }
            System.out.println(minimax(matrix));
        }
        in.close();
    }
}
