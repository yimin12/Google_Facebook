package Contest.NineChapter.HighFreq.Array;

import java.util.TreeMap;

public class SubArraySumClosest {

    public int[] subarraySum(int[] nums){
        int[] res = new int[2];
        if(nums == null || nums.length == 0) return res;
        int closet = Integer.MAX_VALUE, sum = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);
        for(int i = 1; i <= nums.length; i ++){
            sum += nums[i-1];
            if(map.containsKey(sum)){
                res[0] = map.get(sum);
                res[1] = i - 1;
                return res;
            }
            Integer greater = map.higherKey(sum);
            if(greater != null && Math.abs(sum - greater) < closet){
                closet = Math.abs(sum - greater);
                res[0] = map.get(greater);
                res[1] = i - 1;
            }
            Integer lower = map.lowerKey(sum);
            if(lower != null && Math.abs(sum - lower) < closet){
                closet = Math.abs(sum - lower);
                res[0] = map.get(lower);
                res[1] = i - 1;
            }
            map.put(sum, i);
        }
        return res;
    }

}
