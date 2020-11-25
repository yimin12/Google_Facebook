package Algorithm.DynamicProgramming.GambleDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/11 0:21
 *   @Description :
 * 	    You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one
 * 	    of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take
 * 	    the first turn to remove the stones.
 * 	    Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you
 * 	    can win the game given the number of stones in the heap.
 * 	    Input: 4
 * 	    Output: false
 *      Explanation:
 * 	    If there are 4 stones in the heap, then you will never win the game;
 * 	        No matter 1, 2, or 3 stones you remove, the last stone will always be  removed by your friend
 */
public class NimGame {

    // Another version of stone game
    // memory search
    public boolean canFirstWin(int n){
        int[] dp = new int[n +1];
        return memorySearch(n, dp);
    }
    private boolean memorySearch(int n, int[] dp){
        if(dp[n] != 0){
            if(dp[n] == 1){
                return false;
            } else {
                return true;
            }
        }
        if(n <= 3){
            dp[n] = 2;
        } else if(n == 4){
            dp[n] = 1;
        } else {
            dp[n] = !(memorySearch(n - 1, dp) && memorySearch(n - 2, dp) && memorySearch(n - 3, dp)) ? 2 : 1; // 2: win, 1: lose
        }
        if(dp[n] == 2){
            return true;
        }
        return false;
    }

    // greedy solution
    public boolean canFirstWinII(int n){
        return (n % 4 != 0);
    }
}
