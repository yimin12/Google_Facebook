package Contest.NineChapter.HighFreq.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 * Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number and the index of the last number.
 *
 * There is at least one subarray that it's sum equals to zero.
 *
 * Example
 * Example 1:
 *
 * Input:  [-3, 1, 2, -3, 4]
 * Output: [0, 2] or [1, 3].
 * Explanation: return anyone that the sum is 0.
 * Example 2:
 *
 * Input:  [-3, 1, -4, 2, -3, 4]
 * Output: [1,5]
 */
public class SubArraySumZero {

    public List<Integer> subarraySum(int[] nums){
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, -1);
        int sum = 0;
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
            if(prefixSum.containsKey(sum)){
                res.add(prefixSum.get(sum) + 1);
                res.add(i);
                break;
            }
            prefixSum.put(sum, i);
        }
        return res;
    }
}
