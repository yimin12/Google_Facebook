package Contest.GodLeft.advanced;

import java.util.Deque;
import java.util.LinkedList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 19:53
 *   @Description :
 *
 */
public class MaximalRectangle {

    public static int maximalRectangle(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int res = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                height[j] = matrix[i][j] == 0 ? 0 : height[j] + 1;
            }
            // calculate the maximum histogram
            res = Math.max(cal(height), res);
        }
        return res;
    }

    // calculate the maximum histogram
    private static int cal(int[] height){
        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < height.length; i ++){
            while(!stack.isEmpty() && height[i] <= height[stack.peekLast()]){
                int j = stack.pollLast();
                int k = stack.isEmpty() ? -1 : stack.peekLast();
                int cur = (i - k - 1) * height[j];
                res = Math.max(res, cur);
            }
            stack.offerLast(i);
        }
        while(!stack.isEmpty()){
            int j = stack.pollLast();
            int k = stack.isEmpty() ? - 1 : stack.peekLast();
            int cur = (height.length - k - 1) * height[j];
            res = Math.max(res, cur);
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] map = { { 1, 0, 1, 0, 0 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, {1, 0, 0, 1, 0} };
        System.out.println(maximalRectangle(map));
    }
}
