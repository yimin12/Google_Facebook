package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/8 0:38
 *   @Description :
 *  	You are climbing a staircase. It takes _n _steps to reach to the top.
 * 	    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 	    Assumption: Given _n _will be a positive integer
 *  Example:
 * 	    Input:  2   Output:  2
 *  Explanation:	 There are two ways to climb to the top.
 *  	1. 1 step + 1 step
 * 	    2. 2 steps
 */
public class ClimbingStairs {

    public int climbStairs(int n){
        if(n == 0) return 0;
        else if(n == 1) return 1;
        else if(n == 2) return 2;
        int[] steps = new int[n+1];
        steps[0] = 0;
        steps[1] = 1;
        steps[2] = 2;
        for(int i = 3; i <= n; i++){
            steps[i] = steps[i - 1] + steps[i - 2];
        }
        return steps[n];
    }

    // Optimize space
    public int climbStairsII(int n){
        if(n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for(int i = 3; i <= n; i++){
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
