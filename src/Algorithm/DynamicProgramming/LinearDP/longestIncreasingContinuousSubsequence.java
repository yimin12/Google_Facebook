package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/3 22:10
 *   @Description :
 * 	Give an integer array，find the longest increasing continuous subsequence in this array.
 * 	An increasing continuous subsequence:
 * 	Can be from right to left or from left to right.
 * 	Indices of the integers in the subsequence should be continuous.
 * 	O(n) time and O(1) extra space.
 * 	For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.
 * 	For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
 */
public class longestIncreasingContinuousSubsequence {

    public int longest(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int res = 1;
        int len = array.length;
        int left = 1;
        for(int i = 1; i < len; i++){
            if(array[i] > array[i-1]){
                left++;
            } else {
                left = 1;
            }
            res = Math.max(res, left);
        }
        int right = 1;
        for(int i = len - 1; i >= 0; i--){
            if(array[i-1] > array[i]){
                right++;
            } else {
                right = 1;
            }
            res = Math.max(res, right);
        }
        return res;
    }
}
