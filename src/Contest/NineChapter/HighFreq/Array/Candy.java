package Contest.NineChapter.HighFreq.Array;

import java.util.Arrays;

/**
 * Description
 * There are N children standing in a line. Each child is assigned a rating value. Given array ratings representing all rating value of these children.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 *
 * Children with a higher rating get more candies than their neighbors.
 *
 * What is the minimum candies you must give?
 *
 * It is guaranteed 0 \leq rating \leq 2\,0000≤rating≤2000。
 *
 * Example
 * Example 1:
 *
 * Input: [1, 2]
 * Output: 3
 * Explanation: Give the first child 1 candy and give the second child
 */
public class Candy {

    public int candy(int[] ratings){
        if(ratings == null || ratings.length == 0) return 0;
        int n = ratings.length;
        int[] count = new int[n];
        Arrays.fill(count, 1);
        int sum = 0, i;
        for(i = 1; i < n; i ++){
            if(ratings[i] > ratings[i - 1]){
                count[i] = count[i - 1] + 1;
            }
        }
        for(i = n - 1; i >= 1; i --){
            sum += count[i];
            if(ratings[i-1] > ratings[i] && count[i-1] <= count[i]){
                count[i - 1] = count[i] + 1;
            }
        }
        sum += count[0];
        return sum;
    }

    /**
     * Description
     * There are N children standing in a line. Each child is assigned a rating value.
     *
     * You are giving candies to these children subjected to the following requirements:
     *
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * Children with the same rating and are located next to each other get the same candies.
     * What is the minimum candies you must give?
     *
     * Example
     * Example 1:
     *
     * Input: 4 7 8 1 6 6 2
     * Output: 12
     *
     * Explanation: 1 + 2 + 3 + 1 + 2 + 2 + 1 = 12
     */
    public int candyII(int[] ratings){
        int n = ratings.length;
        if(n == 0){
            return 0;
        }
        int[] count = new int[n];
        for(int i = 0; i < n; i ++){
            if(i == 0 || ratings[i] < ratings[i - 1]){
                count[i] = 1;
            } else if(ratings[i] == ratings[i- 1]){
                count[i] = count[i - 1];
            } else {
                count[i] = count[i - 1] + 1;
            }
        }
        int res = 0;
        for(int i = n - 2; i >= 0; i --){
            if(ratings[i] > ratings[i + 1]){
                count[i] = Math.max(count[i], count[i + 1] + 1);
            } else if(ratings[i] == ratings[i + 1]){
                count[i] = Math.max(count[i], count[i + 1]);
            }
            res += count[i];
        }
        res += count[n - 1];
        return res;
    }
}
