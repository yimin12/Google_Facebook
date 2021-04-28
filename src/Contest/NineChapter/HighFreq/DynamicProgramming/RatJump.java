package Contest.NineChapter.HighFreq.DynamicProgramming;

/**
 * Description
 * There is a mouse jumping from the top of a staircase with height n. This mouse can jump 1, 3 or 4 steps in an even number of jumps and 1, 2, or 4 steps in an odd number of times. Some steps have glue,if the mouse jump those steps,it will be directly stuck and cannot continue to jump.You need to solve how many ways the mouse can reach the ground ( level 0 ) from the top of this staircase.It also can be reached if it exceeds the ground. For example, jumping from 1 to -1 is another plan for jumping from 1 to 0.The state of the stairs with or without glue is input from high to low, that is, arr[0] is the top of the stairs.
 * arr[i] == 0 represents that there is no glue on the i-th position, arr[i] == 1 represents that there is glue on the i-th position.
 *
 * 2<=n<=50000
 * arr[i]=1 means there is glue on the step, 0 means there is no glue
 * The input guarantees the highest steps and the lowest is 0
 * The answer needs to be modulo by 1e9 + 7
 *
 * Example
 * Example1:
 *
 * Input:
 * [0,0,0]
 * Output:
 * 5
 * Explanation:
 * There are 3 steps.
 * The step 2  is the starting point without glue.
 * Step 1, no glue.
 * Step 0, no glue.
 * The mouse jump plans is:
 * 2--odd(1)-->1--even(1)-->0
 * 2--odd(1)-->1--even(3)-->-2
 * 2--odd(1)-->1--even(4)-->-3
 * 2--odd(2)-->0
 * 2--odd(4)-->-2
 */
public class RatJump {

    public int ratJump(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n + 3][2]; // you can jump n + 3 mostly
        dp[0][0] = 1;

        int[] even = {1, 3, 4};
        int[] odd = {1, 2, 4};
        int MOD = (int)1e9 + 7;
        for(int i = 0; i < n + 3; i ++){
            for(int j : odd){
                if(!isvalid(arr, i - j)) continue;
                dp[i][1] += dp[i - j][0];
                dp[i][1] %= MOD;
            }
            for(int j : even){
                if(!isvalid(arr, i - j)) continue;
                dp[i][0] += dp[i - j][1];
                dp[i][0] %= MOD;
            }
        }
        long res = 0;
        for(int i = n - 1; i < n + 3; i ++){
            res += dp[i][0];
            res %= MOD;
            res += dp[i][1];
            res %= MOD;
        }
        return (int) res;
    }

    private boolean isvalid(int[] arr, int pos){
        if(pos < 0 || pos >= arr.length - 1 || arr[pos] == 1) return false;
        return true;
    }
}
