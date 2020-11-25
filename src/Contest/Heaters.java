package Contest;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/9 23:39
 *   @Description :
 *
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
 *
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.
 *
 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
 *
 * Input: [1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 */
public class Heaters {

    // Time: O(nlogm);
    public int findRadius(int[] houses, int[] heaters){
        // Assume that all given input is valid
        Arrays.sort(heaters);
        int res = Integer.MIN_VALUE;
        for(int house : houses){
            int index = Arrays.binarySearch(heaters, house);
            if(index < 0){
                index = -(index + 1); // small trick for find the largest smaller than target
            }
            int left = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE; // out of bounder, throw max
            int right = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE; // only one of left or right would be out of boundary
            res = Math.max(res, Math.min(left, right));
        }
        return res;
    }
}
