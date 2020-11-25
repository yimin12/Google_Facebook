package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/4 19:01
 *   @Description :
 *  	Given an array A of non-negative integers, you are initially positioned at index 0
 *  	of the array. A[i] means the maximum jump distance from that position (you can only
 *  	jump towards the end of the array).Determine if you are able to reach the last index.
 *  Assumption:
 *  	The given array is not null and has length of at least 1
 *   Examples:
 *  	{1, 3, 2, 0, 3}, we are able to reach the end of array
 * 	    (jump to index 1 then reach the end of the array)
 *  	{2, 1, 1, 0, 2}, we are not able to reach the end of array
 */
public class ArrayHopper {

    public boolean canJump(int[] array){
        if(array == null || array.length == 0){
            return true;
        }
        boolean[] dp = new boolean[array.length];
        dp[0] = true;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && (j + array[j]) >= i){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[array.length - 1];
    }

    // greedy Solution
    public boolean canJumpII(int[] array){
        if(array == null || array.length == 0){
            return true;
        }
        int cur = 0, next = 0;
        for(int i = 0; i < array.length; i++){
            if(i > cur){
                if(cur == next){
                    return false;
                }
                cur = next;
            }
            next = Math.max(next, i + array[i]);
        }
        return true;
    }

    // Fellow Up: min steps to the end
    public int minJump(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int len = array.length;
        int[] minJump = new int[array.length];
        minJump[0] = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = i - 1; j >= 0; j--){
                // i is the latest index, j is the index already traversed. minJump[j] = -1, means that the index j could not be reached
                if(j + array[j] >= i && minJump[j] != -1){
                    if(minJump[i] == -1 || minJump[j] + 1 < minJump[i]){
                        minJump[i] = minJump[j] + 1;
                    }
                }
            }
        }
        return minJump[array.length - 1];
    }

    // greedy solution
    public int minJumpII(int[] array){
       if(array == null || array.length == 0){
           return 0;
       }
       int jump = 0;
       int cur = 0, next = 0;
       for(int i = 0; i < array.length; i++){
           // need to update cur
           if(i > cur){
               if(i == next){
                   return -1;
               }
               jump++;
               cur = next;
           }
           next = Math.max(next, array[i] + i);
       }
       return jump;
    }
}
