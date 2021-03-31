package Contest.Bloomberg;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/28 20:25
 *   @Description :
 *
 */
public class Solution {

    public String minWindow(String s, String t) {
        if(s == null || t == null || t.length() == 0 || s.length() < t.length()) return "";
        Map<Character, Integer> map = toMap(t);
        int slow = 0, fast, resLen = Integer.MAX_VALUE, start = 0, matchCount = 0;
        for(fast = 0; fast < s.length(); fast ++){
            char c = s.charAt(fast);
            Integer count = map.get(c);
            if(count == null){
                continue;
            } else if(count == 1){
                map.put(c, count - 1);
                matchCount ++;
            } else {
                map.put(c, count - 1);
            }
            while(matchCount == map.size()){
                c = s.charAt(slow);
                count = map.get(c);
                if(count == null) {

                } else if (count < 0){
                    map.put(c, count + 1);
                } else if(count == 0){
                    map.put(c, count + 1);
                    matchCount --;
                    if(resLen > fast - slow){
                        resLen = fast - slow + 1;
                        start = slow;
                    }
                }
                slow ++;
            }
        }
        return s.substring(start, start + resLen);

    }

    private Map<Character, Integer> toMap(String t){
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }


}