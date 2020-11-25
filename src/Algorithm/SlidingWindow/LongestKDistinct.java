package Algorithm.SlidingWindow;

import java.util.HashSet;
import java.util.Set;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/30 23:15
 *   @Description :
 *      Given a string, find the length of the longest substring T that contains at most 2 distinct characters
 *	    For example, Given s = “eceba",
 *	    T is "ece" which its length is 3.
 *
 *
 */
public class LongestKDistinct {

    // Question 1: at most 2 distinct character
    public int lengthTwoDistinct(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        int start = 0, last = -1, max = 0;
        for(int cur = 1; cur < len; cur++){
            if(s.charAt(cur) != s.charAt(cur - 1)){
                if(last > 0 && s.charAt(cur) != s.charAt(last)){
                    max = Math.max(max, cur - start);
                    start = last + 1;
                }
            }
        }
        return Math.max(max, len - start);
    }

    // Follow 1: at most k distinct character
    public int lengthOfKDistinct(String s, int k){
        int[] count = new int[256];
        int start = 0, distinct = 0, max = 0;
        for(int i = 0; i < s.length(); i++){
            if(count[s.charAt(i)] == 0) distinct++;
            count[s.charAt(i)]++;
            while(distinct > k){
                count[s.charAt(start)]--;
                if(count[s.charAt(start)] == 0) distinct--;
                start++;
            }
            max = Math.max(i - start + 1, max);
        }
        return max;
    }

    // dfs version of at most k distinct character
    public int lengthOfKDistinctDFS(String s, int k){
        // base case
        if(s == null || s.length() < k){
            return 0;
        }
        int[] count = new int[26];
        char[] ca = s.toCharArray();
        int len = s.length();
        for(int i = 0; i < len; i++){
            count[ca[i] - 'a']++;
        }
        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i < 26; i++){
            if(count[i] < k && count[i] > 0){
                set.add((char)('a' + i));
            }
        }
        if(set.size() == 0){
            return s.length();
        }
        int left = 0, right = 0;
        int max = 0;
        while(left < len && right < len){
            while(right < len && !set.contains(ca[right])){
                right++;
            }
            max = Math.max(max, lengthOfKDistinctDFS(s.substring(left, right), k));
            if(right == len){
                break;
            }
            right++;
            left = right;
        }
        return max;
    }
    // Follow 2: all distinct without repeating
    public int lengthOfOneDistinct(String s){
        boolean[] exist = new boolean[256];
        int start = 0, max = 0;
        for(int i = 0; i < s.length(); i++){
            while(exist[s.charAt(i)]){
                exist[s.charAt(start++)] = false;
            }
            exist[s.charAt(i)] = true;
            max = Math.max(i - start + 1, max);
        }
        return max;
    }
}
