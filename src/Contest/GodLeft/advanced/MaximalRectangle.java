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

    public static int maxRecSize(int[][] board){
        if(board == null || board.length == 0 || board[0].length == 0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[board[0].length];
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[0].length; j ++){
                height[j] = board[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    public static int maxRecFromBottom(int[] height){
        if(height == null || height.length == 0){
            return 0;
        }
        int maxArea = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        for(int i = 0; i < height.length; i ++){
            while(!stack.isEmpty() && height[i] <= height[stack.peekLast()]){
                int j = stack.pollLast();
                int k = stack.isEmpty() ? -1 : stack.peekLast();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.offerLast(i);
        }
        while(!stack.isEmpty()){
            int j = stack.pollLast();
            int k = stack.isEmpty() ? -1 : stack.peekLast();
            int cur = (height.length - 1 - k) * height[j];
            maxArea = Math.max(cur, maxArea);
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[][] map = { { 1, 0, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 0 }, };
        System.out.println(maxRecSize(map));
    }
}
