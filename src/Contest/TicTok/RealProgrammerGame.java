package Contest.TicTok;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/22 21:19
 *   @Description :
 *     一个m面的骰子，投掷k次。将每次面朝向上的的点数求和（每个面朝上的概率相等），设总点数为s，求s大于N的概率是多少
 *      解法1：暴力枚举  实现方法：dfs+剪枝 or 三层循环
 *      解法2：记忆化搜索 or dp （线性时间）
 */
public class RealProgrammerGame {

    /**
     *  思路：假设f(m,n)表示投第m个骰子的时候，点数之和为n出现的次数，投第m个骰子的点数之和只与投第m-1个骰子有关。我们得到递归方程：
     *
     *  f(k,m)=f(k-1,m)+f(k-1,m-1)+f(k-1,m-2)+f(k-1,m-3)+f(k-1,m-4)+....+f(k-1,0)
     */
    public boolean canWinDPRolling(int N, int M, int k){
        if(N < 0 || M < 0 || k < 0){
            return false;
        }
        // rolling array: dp[i][j] : i means the ith attack, j means the total attack
        int[][] dp = new int[2][k * M + 1]; // table for every possible sum
        // init table and base case, first attack
        int flag = 0;
        for(int i = 0; i <= M; i++){
            dp[flag][i] = 1;
        }
        for(int i = 2; i <= k; i++){
            flag = 1 - flag;
            for(int j = 0; j <= k*M; j++){
                if(j > i * M) break;
                dp[flag][j] = dp[1-flag][j];
                if(j > 0) dp[flag][j] += dp[flag][j-1];
                if(j > M) dp[flag][j] -= dp[1-flag][j-M-1];
            }
        }

        int[] result = dp[flag];
        int total = 0, win = 0;
        for(int i = 0; i <= k*M; i++){
            total += result[i];
            if(i >= N){
                win += result[i];
            }
        }
        double res = (double)win/total;
        System.out.println(res);
        return res > 0.5;
    }

    public boolean canWinDP(int N, int M, int k){
        if(N < 0 || M < 0 || k < 0){
            return false;
        }
        // rolling array: dp[i][j] : i means the ith attack, j means the total attack
        int[][] dp = new int[k+1][k * M + 1]; // table for every possible sum
        // init table and base case, first attack
        for(int i = 0; i <= k * M; i++){
            dp[0][i] = 0;
            if(i <= M){
                dp[1][i] = 1;
            } else {
                dp[1][i] = 0;
            }
        }
        // Induction rule
        for(int i = 2; i <= k; i++){
            for(int j = 0; j <= k * M; j++){
                if(j > i * M) {
                    break;
                }
                dp[i][j] = dp[i-1][j];
                if(j <= M && j > 0) dp[i][j] += dp[i][j-1];
                else if(j > M) dp[i][j] = dp[i][j] + dp[i][j-1] - dp[i-1][j-M-1]; // Time Optimization
//                for(int l = 1; l <= M; l++){
//                    if(j - l < 0) continue;
//                    dp[i][j] = dp[i][j] + dp[i-1][j-l];
//                }
            }
        }
        int[] result = dp[k];
        int total = 0, win = 0;
        for(int i = 0; i <= k*M; i++){
            total += result[i];
            if(i >= N){
                win += result[i];
            }
        }
        double res = (double)win/total;
        return res > 0.5;
    }



    // DFS with pruning
    public boolean canWin(int N, int M, int k){
        if(N < 0 || M < 0 || k < 0){
            return false;
        }
        double[] res = {0, 0}; // res[0] means # of win,  res[0] means # of loss
        dfs(N, M, k, 0, 0, res);
        double win = res[0], loss = res[1];
        System.out.println(win + " " + loss + " " + win/(loss+win));
        return win/loss > 0.5;
    }

    // early return will result in wrong answer if you not handle corner case
    private void dfs(int N, int M, int k, int level, int count, double[] res){
        if(level == k){
            if(count >= N){
                res[0]++;
            } else {
                res[1]++;
            }
            return;
        }
        // enumerate all possible attack damage
        for(int i = 0; i <= M; i++){
            dfs(N, M, k, level + 1, count + i, res);
        }
    }

    public static void main(String[] args) {
        RealProgrammerGame solution = new RealProgrammerGame();
//        boolean res = solution.canWinDPRolling(2, 1, 3);

        boolean res = solution.canWinDPRolling(10, 5, 2);
        System.out.println(res);
    }
}
