package Algorithm.BasicAlgorithm;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/31 13:13
 *   @Description :
 *      https://leetcode.com/problems/first-missing-positive/
 */
public class FirstMissingNumber {

    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0){
            return 1;
        }
        int i = 0;
        while(i < nums.length){
            // nums[nums[i] - 1] != nums[i] -> nums[i] is not in the right position
            if(nums[i] >= 1 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
                swap(nums, nums[i] - 1, i);
            } else {
                i ++;
            }
        }
        // nums are in the right position
        i = 0;
        while(i < nums.length && nums[i] == i + 1) i ++;
        return i + 1;
    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
