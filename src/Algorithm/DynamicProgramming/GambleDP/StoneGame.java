package Algorithm.DynamicProgramming.GambleDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/10 17:32
 *   @Description :
 *   	Two players (A and B) are playing a game with stones. Player A always plays first, and the two players move in alternating
 * 	    turns. In a single move, a player can remove 2,3,5 stones from the game board. If a player is unable to make a move,
 *	    that player loses the game;
 */
public class StoneGame {

    // case 1: you can only remove 2, 3, 5, memory search
    public boolean firstWillWin(int n){
        if(n <= 0){
            return true;
        }
        boolean[] dp = new boolean[n + 1];
        boolean[] visited = new boolean[n + 1];
        return memorySearchI(n, dp, visited);
    }

    private boolean memorySearchI(int n, boolean[] dp, boolean[] visited){
        if(visited[n]){
            return dp[n];
        }
        // base case
        if(n == 0 || n == 1 || n == 4){
            return false;
        } else if(n == 2 || n == 3 || n == 5){
            return true;
        } else {
            dp[n] = !(memorySearchI(n - 2, dp, visited) && memorySearchI(n - 3, dp, visited) && memorySearchI(n - 5, dp, visited));
        }
        visited[n] = true;
        return dp[n];
    }
}
