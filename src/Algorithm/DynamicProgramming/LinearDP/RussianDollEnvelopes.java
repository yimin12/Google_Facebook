package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/9 14:43
 *   @Description :
 *      Give a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 *      Find the maximum number of nested layers of envelopes.
 */

import java.util.Arrays;

/**
 * Input：[[5,4],[6,4],[6,7],[2,3]]
 * Output：3
 * Explanation：
 * the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {

    // Pure dp: O(n^2);
    public int maxEnvlopes(int[][] envelopes){
        if(envelopes == null || envelopes.length == 0){
            return 0;
        }
        // sort it by its envelopes' length in ascending order
        Arrays.sort(envelopes, (a, b)-> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int n = envelopes.length;
        int[] dp = new int[n];
        int res = 0;
        for(int i = 0; i < n; i++){
            // base case and case 1
            dp[i] = 1;
            // case 2:
            for(int j = 0; j < i; j++){
                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // O(nlogn) two dimension of longest sub sequence
    public int maxEnvelopesOpt(int[][] envelopes){
        if(envelopes == null || envelopes.length == 0){
            return 0;
        }
        // sort it by its envelopes' length in ascending order
        Arrays.sort(envelopes, (a, b)-> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int n = envelopes.length;
        int[] dp = new int[n + 1];
        dp[0] = Integer.MIN_VALUE;
        for(int i=1; i <= n; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        int res = 0;
        for(int i = 0; i < n; i++){
            int index = findIndex(dp, envelopes[i][1]);
            dp[index] = envelopes[i][1];
            res = Math.max(res, index);
        }
        return res;
    }
    private int findIndex(int[] dp, int target){
        int l = 0, r = dp.length - 1;
        while(l + 1 < r){
            int mid = l + (r - l)/2;
            if(dp[mid] >= target){
                r = mid;
            } else{
                l = mid;
            }
        }
        if(dp[l] >= target){
            return l;
        }
        return r;
    }
}
