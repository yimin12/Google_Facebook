package Contest.Bloomberg;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/5 23:14
 *   @Description :
 *
 */
public class WordBreak {


    // ------------------------------------------------------------------wordBreakII
    public List<String> wordBreak(String s, List<String> wordDict) {
        // memory search
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict == null){
            return res;
        }
        Set<String> set = toSet(wordDict);
        Map<String, List<String>> memo = new HashMap<>();
        return wordBreakHelper(memo, s, set);
    }

    // each possibilities should only be traversed once, just calculate how many possibilities.
    // O(n^2)
    private List<String> wordBreakHelper(Map<String, List<String>> memo, String s, Set<String> set){
        if(memo.containsKey(s)){
            return memo.get(s);
        }
        List<String> res = new ArrayList<>();
        if(set.contains(s)){ // base case, contain the entire string
            res.add(s);
        }
        // try to split the given string
        for(int i = 1; i < s.length(); i ++){
            // contains break or not break
            String prefix = s.substring(0, i); // O(n)
            if(set.contains(prefix)){
                String suffix = s.substring(i, s.length());
                for(String su : wordBreakHelper(memo, suffix, set)){
                    res.add(prefix + " " + su);
                }
            }
        }
        memo.put(s, res);
        return res;
    }

    private Set<String> toSet(List<String> words){
        Set<String> set = new HashSet<>();
        for(String s : words){
            set.add(s);
        }
        return set;
    }
    // O(n^3) -----------------------------------------------------------word Break 1
    public boolean wordBreakDP(String s, List<String> wordDict) {
        if(s == null || wordDict == null) return false;
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i ++){
            for(int j = 0; j < i; j ++){
                dp[i] |= dp[j] && wordDict.contains(s.substring(j, i));
            }
        }
        return dp[s.length()];
    }

}
