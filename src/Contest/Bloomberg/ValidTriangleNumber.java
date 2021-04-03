package Contest.Bloomberg;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/2 23:57
 *   @Description :
 *
 */

/**
 * Given an array consists of non-negative integers,
 * your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 */
public class ValidTriangleNumber {

    public int triangleNumber(int[] nums) {
        if(nums == null || nums.length <= 2) return 0;
        Arrays.sort(nums);
        int res = 0, n = nums.length;
        for(int i = n - 1; i >= 2; i --){
            int left = 0, right = i - 1;
            while(left < right){
                if(nums[left] + nums[right] > nums[i]){
                    res += right - left; // all vertices contains i can be valid triangle number
                    right --;
                } else {
                    left ++;
                }
            }
        }
        return res;
    }
}
