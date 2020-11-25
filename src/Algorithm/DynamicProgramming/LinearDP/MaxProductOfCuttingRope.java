package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/4 22:22
 *   @Description :
 * 	    Given a rope with positive integer-length n, how to cut the rope into
 * 	    m integer-length parts with length p[0], p[1], ...,p[m-1], in order to
 * 	    get the maximal product of p[0]*p[1]* ... *p[m-1]? m is determined
 * 	    by you and must be greater than 0 (at least one cut must be made).
 * 	    Return the max product you can have.
 *      Assumption: n>=2
 *  Examples:
	    n = 12, the max product is 3 * 3 * 3 * 3 = 81
	    (cut the rope into 4 pieces with length of each is 3).
 */
public class MaxProductOfCuttingRope {
    public int maxProduct(int length){
        if(length < 2){
            return 0;
        }
        if(length == 2){
            return 1;
        }
        int[] dp = new int[length + 1];
        dp[1] = 0;
        dp[2] = 1;
        for(int i = 3; i < dp.length; i++){
            for(int j = 1; j < i/2; j++){
                dp[i] = Math.max(dp[i], j * Math.max(i - j, dp[i-j]));
            }
        }
        return dp[length];
    }
}


