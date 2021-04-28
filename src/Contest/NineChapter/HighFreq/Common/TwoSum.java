package Contest.NineChapter.HighFreq.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description
 * Given an array of integers that is already sorted in ascending absolute order, find two numbers so that the sum of them equals a specific number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Note: the subscript of the array starts with 0
 *
 * You are not allowed to sort this array.
 *
 * It is guaranteed that all numbers in the numsnums is distinct.
 * The length of numsnums is \leq 100\,000≤100000.
 * The number in numsnums is \leq 10^9≤10

 * Example
 * Input:
 * [0,-1,2,-3,4]
 * 1
 * Output:
 * [[1,2],[3,4]]
 * Explanation:
 * nums[1] + nums[2] = -1 + 2 = 1, nums[3] + nums[4] = -3 + 4 = 1
 * You can return [[3,4],[1,2]], the system will automatically help you sort it to [[1,2],[3,4]]. But [[2,1],[3,4]] is invaild.
 */
public class TwoSum {

    public List<List<Integer>> twoSumVII(int[] nums, int target) {
        // write your code here
        int most = Integer.MIN_VALUE, least = Integer.MAX_VALUE;
        int mostIndex = 0, leastIndex = 0;

//        get the different start point for biggest and smallest elements
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] > most) {
                most = nums[index];
                mostIndex = index;
            }
            if (nums[index] < least) {
                least = nums[index];
                leastIndex = index;
            }
        }


        List<List<Integer>> res = new ArrayList<>();

        int start = leastIndex;
        int end = mostIndex;
        while (nums[start] < nums[end]) {
            if (nums[start] + nums[end] == target) {
                res.add(Arrays.asList(Math.min(start, end), Math.max(start, end)));
                end = findNextSmallerPos(nums, end);
                start = findNextBiggerPos(nums, start);
            } else if (nums[start] + nums[end] > target) end = findNextSmallerPos(nums, end);
            else start = findNextBiggerPos(nums, start);
        }
        return res;
    }

    private int findNextSmallerPos(int[] nums, int cur) {
        if (nums[cur] <= 0) {
            while (cur < nums.length - 1) {
                cur++;
                if (nums[cur] < 0) return cur;
            }

        } else {
            while (cur > 0) {
                cur--;
                if (nums[cur] >= 0) return cur;
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0) return i;
            }
        }
        return cur;
    }

    private int findNextBiggerPos(int[] nums, int cur) {
        if (nums[cur] >= 0) {
            while (cur < nums.length - 1) {
                cur++;
                if (nums[cur] > 0) return cur;
            }
        } else {
            while (cur > 0) {
                cur--;
                if (nums[cur] <= 0) return cur;
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) return i;
            }
        }
        return cur;
    }
}
