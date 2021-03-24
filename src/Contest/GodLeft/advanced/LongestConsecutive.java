package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 21:58
 *   @Description :
 *
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 给定无序数组 arr，返回其中最长的连续序列的长度
 * 【举例】 arr=[100,4,200,1,3,2]，最长的连续序列为[1,2,3,4]，所以返回4。
 */
public class LongestConsecutive {


    // sorting it first at least consume O(nlogn) time
    public static int longestConsective(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int max =1;
        // key : curValue, value: longest consecutive length that start or end in curValue
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < array.length; i ++){
            if(!map.containsKey(array[i])){
                map.put(array[i], 1);
                // merge interval
                if(map.containsKey(array[i] - 1)){
                    max = Math.max(max, mergeInterval(map, array[i]-1, array[i]));
                }
                if(map.containsKey(array[i] + 1)){
                    max = Math.max(max, mergeInterval(map, array[i], array[i] + 1));
                }
            }
        }
        return max;
    }

    private static int mergeInterval(Map<Integer, Integer> map, int leftValue, int rightValue){
        int left = leftValue - map.get(leftValue) + 1;
        int right = rightValue + map.get(rightValue) - 1;
        int len = right - left + 1;
        map.put(leftValue, len);
        map.put(rightValue, len);
        return len;
    }
}
