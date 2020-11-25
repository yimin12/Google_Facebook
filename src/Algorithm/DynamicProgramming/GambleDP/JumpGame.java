package Algorithm.DynamicProgramming.GambleDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/12 19:44
 *   @Description :
 *	    Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *	    Each element in the array represents your maximum jump length at that position
 *	    Determine if you are able to reach the last index
 */
public class JumpGame {

    public boolean canJump(int[] array) {
        // sanity check
        if(array == null || array.length == 0) return false;
        if(array.length == 1) return true;
        boolean[] dp = new boolean[array.length];
        // base case :
        dp[0] = true;
        for(int i = 1; i < array.length; i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] && j + array[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[array.length - 1];
    }
    // Solution 2: DP, dp[i] means if you can jump from i to the end
    // Time: O(n^2), Space: Extra O(n) for dp
    public boolean canJumpR(int[] array) {
        if(array == null || array.length == 0) return false;
        if(array.length == 1) return true;
        boolean[] dp = new boolean[array.length];
        // base case;
        dp[array.length - 1] = true;
        for(int i = array.length - 2; i >= 0; i--) {
            if(i + array[i] >= array.length - 1) {
                dp[i] = true;
            } else {
                for(int j = array[i]; j > 0; j--) {
                    if(dp[i+j] == true) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[0];
    }
    // Solution 3: Greedy Solution. Go from left to right, check if the rightmost can be reach
    // Greedy algorithm: Time ~ O(N), Space ~ O(1)
    public boolean canJumpGreedy(int[] array) {
        if(array == null || array.length == 0) return false;
        if(array.length == 1) return true;
        int reach = 0; // record rightmost that can jump to
        for(int i = 0; i <= reach && reach < array.length; i++) {
            reach = Math.max(reach,  i + array[i]);
        }
        return reach >= array.length - 1;
    }
    // Solution 4: Greedy with DP , DP: Time ~ O(N), Space ~ O(1)
    // Key insight, we try one step further to identify
    public boolean canJumpGreedyDP(int[] array) {
        if(array == null || array.length == 0) return false;
        if(array.length == 1) return true;
        int cur = 0, next = 0;
        for(int i = 0; i < array.length; i++) {
            if(i > cur) {
                if(cur == next) {
                    return false;
                }
                cur = next;
            }
            next = Math.max(next, i + array[i]);
        }
        return true;
    }

    // Follow Up 2: Determine the minimum number of jumps you need to reach the end of array. If can not reach, return -1;
    // Solution 1: pure Dynamic Programming Time: O(n^2), Space: Extra O(n) for dp
    public int canJumpCountDP(int[] array) {
        // Assumption
        if(array == null || array.length == 0) return -1;
        if(array.length == 1) return 0;
        int[] dp = new int[array.length];
        // base case
        dp[0] = 0;
        for(int i = 1; i < array.length; i++) {
            dp[i] = -1; // initialized as unreachable
            for(int j = i - 1; j >= 0 ; j++) {
                if(j + array[j] >= i && dp[j] != -1) {
                    if(dp[i] == -1 || dp[j] + 1 < dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }
        return dp[array.length - 1];
    }
    // Solution 2: Greedy Solution
    // Greedy algorithm: Time ~ O(N), Space ~ O(1)
    public int canJumpCountGreedy(int[] array) {
        if(array == null || array.length == 0) return -1;
        if(array.length == 1) return 0;
        int jump = 0, cur = 0, next = 0;
        for(int i = 0; i < array.length; i++) {
            if(i > cur) {
                jump++;
                if(cur == next) {
                    return -1;
                }
                cur = next;
            }
            next = Math.max(next, i + array[i]);
        }
        return jump;
    }
}
