package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 22:55
 *   @Description :
 *      Description:
 * 	    Given an array A[0]...A[n-1] of integers, find out the length of the
 * 	    longest ascending subsequence.
 *  Assumption:
 *  	A is not null;
 *  Examples:
	    Input: A = {5, 2, 6, 3, 4, 7, 5}
	    Output: 4
	    Because [2, 3, 4, 5] is the longest ascending subsequence.
 */
public class LongestAscendingSubArray {

    public int longest(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int res = 1;
        int cur = 1;
        for(int i = 1; i < array.length; i++){
            if(array[i] > array[i - 1]){
                cur++;
                res = Math.max(cur, res);
            } else {
                cur = 1;
            }
        }
        return res;
    }

    //* Description:
    //* 	Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.
    //* Assumption:
    //* 	A is not null
    //* Examples:
    //* 	Input: A = {5, 2, 6, 3, 4, 7, 5}
    //	Output: 4
    //	Because [2, 3, 4, 5] is the longest ascending subsequence.
    public int longestSubSequence(int[] array) {
        if(array.length == 0) {
            return 0;
        }
//		dp[i] = the length of longest ascending subsequence ending in index i
        int[] longest = new int[array.length];
        int result = 1;
        for(int i = 0; i < array.length; i++) {
//			set the default value of dp[i] is 1
            longest[i] = 1;
            for(int j = 0; j < i; j++) {
//				only when array[j] < array[i], it is possible to use the longest ascending subsequence
//				ending at index j and array[i] to form a new ascending subsequence
                if(array[j] < array[i]) {
                    longest[i] = Math.max(longest[j] + 1, longest[i]);
                }
            }
//			possibly update the global longest one.
            result = Math.max(longest[i], result);
        }
        return result;
    }
    //	Method 2: O(nlogn)
    public int longestII(int[] array) {
        if(array.length == 0) return 0;
//		tbl[i]: the smallest ending value of all the ascending subsequences with length i
        int[] tbl = new int[array.length];
//		at the very begining, the longest ascending subsequence we have has length 1(array[0] itself)
        int result = 1;
//		initialization: at the very beginning, we have a length 1 ascending subsequence, ending value as
//		array[0]. While we traversing the array, we will update existing tbl[i] and find new longer ascending
//		subsequence
        tbl[1] = array[0];
        for(int i = 1; i < array.length; i++) {
//			tbl is guaranteed to be in ascending order - key point
//			from tbl, find the best (longest) ascending subsequence, which can concatenate array[i] to
//			from the new one. this is actually binary search of "largest smaller value"
            int index = find(tbl, 1, result, array[i]);
//			two cases:
//			1. we can possibly form a longer ascending subsequence than whatever we have before, if array[i]
//			is larger than all values in tbl.
//			2. we may update tbl[index+1] because we find a better ascending subsequence with length
//			index + 1 (the ending value is smaller or equal)
            if(index == result) {
                tbl[++result] = array[i];
            } else {
                tbl[index + 1] = array[i];
            }
        }
        return result;
    }
    //	find the index of the "largest smaller value" to target in the tbl, tbl is sorted in ascending order
    private int find(int[] tbl, int left, int right, int target) {
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(tbl[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
