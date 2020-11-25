package Algorithm.DynamicProgramming.LinearDP;

import java.util.Deque;
import java.util.LinkedList;
import java.util.zip.CheckedInputStream;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 20:51
 *      Description:
 * 	Determine the largest square of 1s in a binary matrix
 * 	    (a binary matrix only contains 0 and 1), return the length of the largest square.
 *  Assumption:
 * 	    The given matrix in not null and guaranteed to be of size N * N and N >= 0
 *   Examples:
 * 	    { {0, 0, 0, 0},
  	    {1, 1, 1, 1},
  	    {0, 1, 1, 1},
	    {1, 0, 1, 1}}
	    the largest square of 1s has length of 2

 */
public class LargestSquaresOfOnes {

    public int largest(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int M = matrix.length, N = matrix[0].length;
        int[][] dp = new int[M][N];
        int res = 0;
        for(int i = 0 ; i < M; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j] == 1){
                    if(i == 0 || j == 0){
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]);
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                    }
                }
                res = Math.max(dp[i][j], res);
            }
        }
        return res;
    }

    // FollowUp: Largest Number of Ones that can form a rectangle
//    Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
//    Example:
//    Input:
//            [
//            ["1","0","1","0","0"],
//            ["1","0","1","1","1"],
//            ["1","1","1","1","1"],
//            ["1","0","0","1","0"]
//            ]
//    Output: 6

    public int largestRectangle(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int M = matrix.length, N = matrix[0].length;
        int[][] dp = new int[M][N];
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j] == 1){
                    if(i == 0) dp[i][j] = 1;
                    if(i > 0) dp[i][j] = dp[i-1][j] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int max = 0;
        for(int [] height:dp){
            max = Math.max(max, largestRectangle(height));
        }
        return max;
    }
    private int largestRectangle(int[] height){
        if(height == null || height.length == 0){
            return 0;
        }
        if(height.length == 1){
            return height[0];
        }
        int maxArea = 0;
        int[] leftBound = new int[height.length];
        int[] rightBound = new int[height.length];
        leftBound[0] = -1;
        rightBound[height.length - 1] = height.length;
        for(int i = 1; i < height.length; i++){
            int l = i - 1;
            while(l >= 0 && height[l] >= height[i]){
                l = leftBound[l];
            }
            leftBound[i] = l;
        }
        for(int i = height.length - 2; i >= 0; i--){
            int r = i + 1;
            while(r < height.length && height[r] >= height[i]){
                r = rightBound[r];
            }
            rightBound[i] = r;
        }
        for(int i = 0; i < height.length; i++){
            maxArea = Math.max(height[i] * (rightBound[i] - leftBound[i] - 1), maxArea);
        }
        return maxArea;
    }

    // Using Monotonous stack
    public int maxialRectangleI(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int M = matrix.length, N = matrix[0].length;
        int[] heights = new int[M];
        int maxArea = -1;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j] == 1){
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            int area = largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
    public int largestRectangleArea(int[] heights){
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int maxArea = 0;
        for(int i = 0; i < heights.length; i++){
            while(stack.peek() != -1 && heights[stack.pop()] >= heights[i]){
                maxArea= Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while(stack.peek() != -1){
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxArea;
    }
}
