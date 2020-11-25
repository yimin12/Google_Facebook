package Algorithm.DynamicProgramming.GambleDP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/11 0:27
 *   @Description :
 *  	In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first
 * 	    causes the running total to reach or exceed 100 wins.
 * 	    What if we change the game so that players cannot re-use integers?
 * 	    For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100
 * 	    Given an integer maxChoosableInteger and another integerdesiredTotal, determine if the first player to move can force a win, assuming
 * 	    both players play optimally.
 * 	    You can always assume thatmaxChoosableIntegerwill not be larger than 20 anddesiredTotalwill not be larger than 300.
 *  Example:
 *  	Input: maxChooseableinteger = 10, desiredTotal = 11 , Output: false
 *  Explanation:
 * 	    No matter which integer the first player choose, the first player will lose.
 * 	    The first player can choose an integer from 1 up to 10.
 * 	    If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * 	    The second player will win by choosing 10 and get a total = 11, which is  >= desiredTotal.
 * 	    Same with other integers chosen by the first player, the second player will always win.
 */
public class HundredGame {

    // Analysis :
    // Most of the "Game Playing" problems on LeetCode can be solved using the top-down DP approach, which
    // "brute-forcely" simulates every possible state of the game.
    // The key part for the top-down dp strategy is that we need to avoid repeatedly solving sub-problems.
    // Instead, we should use some strategy to "remember" the outcome of sub-problems. Then when we see them again, we instantly know their result.
    // For this question, the key part is : what is the state of the game?
    // 	1. The unchosen numbers
    // 	2. The remaining desiredTotal to reach
    // A second thought reveals that1)and2)are actually related because we can always get the2)by deducting the sum of chosen numbers from original desiredTotal.

    // Time: O(2^n) Space: O(2^n)
    public boolean canFirstWin(int maxChoosableInteger, int desiredInteger){
        int sum = ((maxChoosableInteger + 1) * maxChoosableInteger) / 2;
        if(sum < desiredInteger){
            return false;
        }
        if(desiredInteger <= maxChoosableInteger){
            return true;
        }
        Map<Integer, Boolean> memory = new HashMap<>();
        boolean[] visited = new boolean[maxChoosableInteger +1];
        return memorySearch(desiredInteger, memory, visited);
    }
    private boolean memorySearch(int desiredInteger, Map<Integer, Boolean> memory, boolean[] visited){
        if(desiredInteger <= 0){
            return false;
        }
        int key = formatKey(visited);
        if(!memory.containsKey(key)){
            for(int i = 1; i < visited.length; i++){
                if(!visited[i]){
                    visited[i] = true;
                    if(!memorySearch(desiredInteger - i, memory, visited)){
                        memory.put(key, true);
                        visited[i] = false;
                        return true;
                    }
                    visited[i] = false;
                }
            }
            memory.put(key, false); // if I pick 1 << i, I can not win
        }
        return memory.get(key);
    }
    private int formatKey(boolean[] visited){
        int key = 1;
        for(boolean b : visited){
            key = key << 1;
            if(b){
                key |= 1;
            }
        }
        return key;
    }

    public static void main(String[] args) {
        HundredGame solution = new HundredGame();
        List<Boolean> res = new ArrayList<>();
        for(int i = 11; i < 15; i++){
            boolean cur = solution.canFirstWin(10, 11);
            res.add(cur);
        }
        System.out.println(res.toString());
    }
}
