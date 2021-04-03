package Contest.Bloomberg;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/2 18:24
 *   @Description :
 *
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int n = nums.length, sign = 1;
        for(int i = 0; i < n - 2; i ++){
            int left = i + 1, right = n - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == target){
                    return target;
                } else if(sum < target){
                    if(target - sum < diff){
                        diff = target - sum;
                        sign = -1;
                    }
                    left ++;
                } else {
                    if(sum - target < diff){
                        diff = sum - target;
                        sign = 1;
                    }
                    right --;
                }
            }
        }
        return target + diff * sign;
    }
}
