package Algorithm.HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/1 12:50
 *   @Description :
 *      Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency,
 *      sort them in decreasing order.
 *      Return the sorted array.
 *      Input: nums = [1,1,2,2,2,3]
 *      Output: [3,1,1,2,2,2]
 *      Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
 */
public class SortArraybyIncreasingFrequency {


    public int[] sortByValue(int[] input){
        if(input == null || input.length == 0){
            return new int[0];
        }
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for(int i : input){
            cnt.put(i, cnt.getOrDefault(i, 0) + 1);
        }
        PriorityQueue pq = new PriorityQueue<Integer>((a, b)-> cnt.get(a) > cnt.get(b) ? 1 : cnt.get(a) == cnt.get(b) ? 0 : -1 );
        for(Map.Entry<Integer, Integer> entry : cnt.entrySet()){
            pq.offer(entry.getKey());
        }
        int[] res = new int[input.length];
        int idx = 0;
        while(!pq.isEmpty()){
            Integer key = (Integer) pq.poll();
            int value = cnt.get(key);
            for(int i = 0; i < value; i++){
                res[idx++] = key;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SortArraybyIncreasingFrequency solution = new SortArraybyIncreasingFrequency();
        int[] input = {1,1,2,2,2,3};
        int[] result = solution.sortByValue(input);
        for(int res : result){
            System.out.print(res + " ");
        }
    }

}
