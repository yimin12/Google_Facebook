package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 23:26
 *   @Description :
 *      Description:
 * 	    Given a array that contains only 1s and 0s, find the largest subsequence which contains only 1s,
 *  Assumption:
 *  	The given array is not null, has size of N .
 *  Examples:
 *  	{0, 0, 1, 1}		the largest cross of 1s has arm length 2.
 */
public class LongestOnes {

    public int longest(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int res = 0, cur =0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == 1){
                if(i == 0 || array[i - 1] == 0){
                    cur = 1;
                } else {
                    cur++;
                }
            }
            res = Math.max(cur, res);
        }
        return res;
    }
}
