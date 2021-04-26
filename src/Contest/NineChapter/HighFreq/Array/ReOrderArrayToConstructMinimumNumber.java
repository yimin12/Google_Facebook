package Contest.NineChapter.HighFreq.Array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Description
 * Construct minimum number by reordering a given non-negative integer array. Arrange them such that they form the minimum number.
 *
 * The result may be very large, so you need to return a string instead of an integer.
 *
 * Example
 * Example 1:
 *
 * Input:[2,1]
 * Output:[1,2]
 *  Explanation: there are 2 possible numbers can be constructed by reordering the array:
 *           1+2=12
 *           2+1=21
 * So after reordering, the minimum number is 12, and return it.
 */
public class ReOrderArrayToConstructMinimumNumber {

    public String minNumber(int[] nums) {
        // write your code here
        int n = nums.length;
        if(n < 1) return "";
        String[] strs = new String[n];
        for(int i = 0; i < n; i ++){
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, new Cmp());
        String res = "";
        for(int i = n - 1; i >= 0; i --){
            res = res.concat(strs[i]);
        }
        int i = 0;
        // handle all leading 0
        while(i < n && res.charAt(i) == '0') i ++;
        if(i == n) return "0";
        return res.substring(i);
    }

    class Cmp implements Comparator<String> {
        @Override
        public int compare(String a, String b){
            String ab = a.concat(b);
            String ba = b.concat(a);
            return ba.compareTo(ab);
        }
    }
}
