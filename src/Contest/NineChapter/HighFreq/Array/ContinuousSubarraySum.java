package Contest.NineChapter.HighFreq.Array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * Given an integer array, find a continuous subarray where the sum of numbers is the biggest. Your code should return the index of the first number and the index of the last number. (If their are duplicate answer, return the minimum one in lexicographical order)
 *
 * Example
 * Example 1:
 *
 * Input: [-3, 1, 3, -3, 4]
 * Output: [1, 4]
 */
public class ContinuousSubarraySum {

    public List<Integer> continuousSubarraySum(int[] A){
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(0);
        int n = A.length;
        int start = 0, end = 0;
        int sum = 0;
        int globalMax = -0x7fffffff;
        for(int i = 0; i < n; i ++){
            if(sum < 0){
                sum = A[i];
                start = end = i;
            } else {
                sum += A[i];
                end = i;
            }
            if(sum > globalMax){
                globalMax = sum;
                res.set(0, start);
                res.set(1, end);
            }
        }
        return res;
    }

    /**
     * Given an circular integer array (the next element of the last element is the first element), find a continuous subarray in it, where the sum of numbers is the biggest. Your code should return the index of the first number and the index of the last number.
     *
     * If duplicate answers exist, return any of them.
     *
     * Example
     * Example 1:
     *
     * Input: [3, 1, -100, -3, 4]
     * Output: [4, 1]
     * Example 2:
     *
     * Input: [1,-1]
     * Output: [0, 0]
     */
    public static List<Integer> continuousSubarraySumII(int[] A){
        List<Integer> res = new ArrayList<>();
        int n = A.length, sum = 0;
        for(int i = 0; i < n; i ++){
            sum += A[i];
        }
        int[] res1 = findMax(A, 1);
        int[] res2 = findMax(A, -1);
        if(res1[2] > (sum += res2[2]) || res2[1] - res2[0] == n - 1){
            res.add(res1[0]);
            res.add(res1[1]);
        } else {
            res.add(((res2[1] + 1) & (n-1)));
//            res.add(((res2[0] + n - 1) & (n-1)));
//            res.add((res2[1] + 1) % n);
            res.add((res2[0] + n - 1) %n);
        }
        return res;
    }

    private static int[] findMax(int[] A, int flag){
        int[] res = new int[3];
        int sum = 0, max = Integer.MIN_VALUE, lo = 0;
        for(int i = 0; i < A.length; i ++){
            sum += A[i] * flag;
            if(max < sum){
                max = sum;
                res[0] = lo;
                res[1] = i;
                res[2] = sum;
            }
            if(sum < 0){
                sum = 0;
                lo = i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,1,-100,-3,4};
        List<Integer> res = continuousSubarraySumII(array);
        System.out.println(res);
    }
}
