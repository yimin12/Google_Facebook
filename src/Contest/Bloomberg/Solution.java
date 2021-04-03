package Contest.Bloomberg;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/28 20:25
 *   @Description :
 *
 */
public class Solution {

    public int findShortestSubArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> freq =  new HashMap<>();
        int min = Integer.MAX_VALUE, degree = 1;
        for(int i = 0; i < nums.length; i ++){
            map.putIfAbsent(nums[i], i); // record the first time apperance
            freq.putIfAbsent(nums[i], 1);
            if(map.get(nums[i]) == i){
                continue;
            } else {
                if(freq.get(nums[i]) == degree){
                    min = Math.min(min, i - map.get(nums[i]));
                }
                freq.put(nums[i], freq.get(nums[i]) + 1);
                degree = Math.max(degree, freq.get(nums[i]));
            }
        }
        return min;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.findShortestSubArray(new int[]{1,2,2,3,1});
        System.out.println(res);
//        Arrays.stream(res).forEach(value -> {
//            System.out.print(value + " ");
//        });
    }


}