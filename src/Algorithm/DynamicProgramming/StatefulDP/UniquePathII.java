package Algorithm.DynamicProgramming.StatefulDP;

import java.util.Collections;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/13 18:46
 *   @Description :
 *	    A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *		The robot can only move either down or right-up or right-down at any point in time.
 *		The robot is trying to reach the top-right corner of the grid (marked 'Finish' in the diagram below).
 *		How many possible unique paths are there?
 *      Compare to UniquePath, we use move in three directions : right-up, right, right-down
 *      from top-left to top-right
 */
public class UniquePathII {

    public int uniquePathI(int m, int n){
        if(m <= 0 || n <= 0){
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int j = 1; j < n; j++){
            for(int i = 0; i < m; i++){
                dp[i][j] += dp[i][j - 1]; // 1: Going down
                if(i > 0){
                    dp[i][j] += dp[i-1][j-1]; // 2: Going right-down
                }
                if(i + 1 < m){
                    dp[i][j] += dp[i+1][j-1];
                }
            }
        }
        return dp[0][n-1];
    }
    // Rolling array
    public int uniquePathII(int m, int n){
        if(m <= 0 || n <= 0){
            return 0;
        }
        int[] dp = new int[m];
        dp[0] = 1;
        int pre, cur;
        for(int i = 1; i < n; i++){
            pre = cur = 0;
            for(int j = 0; j < m; j++){
                pre = cur;
                cur = dp[j];
                if(j > 0){
                    dp[j] += pre;
                }
                if(j + 1 < m){
                    dp[j] += dp[j + 1];
                }
            }
        }
        return dp[0];
    }

    // Find how many ways to cross given points
    // followup3: 给定矩形里的三个点，找到遍历这三个点的所有路径数量
    class Point{
        public int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    // -1: it can not across these points in a time
    public int uniquePathIII(int m, int n, List<Point> points){
        if(m <= 0 || n <= 0 || points == null){
            return 0;
        }
        Collections.sort(points, (a, b) -> a.y != b.y ? a.y - b.y : a.x - b.x);
        int[][] dp = new int[m][n];
        int k = points.size();
        // we should take care about the points in the first column
        int count = 0;
        for(Point point : points){
            if(point.y == 0){
                count++;
            }
        }
        if(count >= k){
            return -1;
        }
        dp[0][0] = 1;
        for(int j = 1; j < n; j++){
            for(int i = 0; i < m; i++){
                dp[i][j] = dp[i][j - 1];
                if(i > 0){
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if(i + 1 < m){
                    dp[i][j] += dp[i + 1][j - 1];
                }
            }
            if(count < k && j == points.get(count).y){
                for(int i = 0; i < m; i++){
                    if(i != points.get(count).x){
                        dp[i][j] = 0;
                    }
                }
                count += 1;
            }
        }
        if(count == k){
            return dp[0][n - 1];
        }
        return -1;
    }
    // Follow up 4: count if the path go through a border
    // followup4: 给定一个下界 (x == H)，找到能经过给定下界的所有路径数量 (x >= H)
    public int uniquePathIV(int m, int n, int h){
        if(m <= 0 || n <= 0 || h < 1){
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int j = 1; j < n; j++){
            for(int i = 0; i < m; i++){
                dp[i][j] = dp[i][j - 1];
                if(i > 0){
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if(i + 1 < m){
                    dp[i][j] += dp[i + 1][j - 1];
                }
            }
        }
        for(int i = 0; i < h; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = 0;
            }
        }
        for(int j = 1; j < n; j++){
            for(int i = h-1; i >= 0; i--){
                dp[i][j] = dp[i][j - 1];
                if(i > 0){
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if(i + 1 < m){
                    dp[i][j] += dp[i + 1][j - 1];
                }
            }
        }
        return dp[0][n-1];
    }

    // Follow Up 5: 起点和终点改成从左上到左下，每一步只能 ↓ ↘ ↙，求所有可能的路径数量
    public int uniquePathV(int m, int n){
        if(m <= 0 || n <= 0){
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = dp[i - 1][j];
                if(j > 0){
                    dp[i][j] += dp[i-1][j-1];
                }
                if(j + 1 < n){
                    dp[i][j] += dp[i-1][j+1];
                }
            }
        }
        return dp[m-1][0];
    }

    public static void main(String[] args) {
        UniquePathII solution = new UniquePathII();
        int res = solution.uniquePathV(6, 7);
        System.out.println(res);
    }
}
