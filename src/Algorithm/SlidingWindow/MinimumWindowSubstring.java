package Algorithm.SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/1 23:25
 *   @Description :
 *      Given a string S and a string T, find the minimum window in S which will contain all the characters in T
 *		Input: S = “ADOBECODEBANC”
 *			   T = “ABC”
 *		Output: “BANC”
 *
 *		Type: Sliding Window of size unfixed
 */
public class MinimumWindowSubstring {

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
                    // do nothing
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
        if(resLen == Integer.MAX_VALUE) return "";
        return s.substring(start, start + resLen);

    }

    private Map<Character, Integer> toMap(String t){
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    // FollowUp 2: You are given a string, S, and a list of words, L, that are all of the same length.
    // Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and
    // without any intervening characters.
    /*
     * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
     * You should return the indices: [0,9]. (order does not matter).
     */
    // Time ~ O(NM), Space ~ O(M) where N = lenS, M = len of L's word

    public List<Integer> findSubstring(String s, String[] array){
        List<Integer> res = new ArrayList<>();
        int wordLen = array[0].length();
        if(s == null || s.length() == 0 || wordLen == 0 || s.length() < wordLen){
            return res;
        }
        Map<String, Integer> dict = wordCount(array);
        for(int i = 0; i < wordLen; i++){
            int matchCount = 0, prev = i;
            Map<String, Integer> word = new HashMap<>();
            for(int j = i; j <= s.length() - wordLen; j+= wordLen){
                String curWord = s.substring(j, j + wordLen);
                if(!dict.containsKey(curWord)){
                    // reset, because it require without any intervening characters
                    matchCount = 0;
                    prev = j + wordLen;
                    word.clear();
                } else {
                    if(!word.containsKey(curWord)) dict.put(curWord, 1);
                    else dict.put(curWord, dict.get(curWord) + 1);
                    if(word.get(curWord) <= dict.get(curWord)) matchCount++;
                    else {
                        while(word.get(curWord) > dict.get(curWord)){
                            String prevWord = s.substring(prev, prev + wordLen);
                            word.put(prevWord, word.get(prevWord) - 1);
                            if(word.get(prevWord) < dict.get(prevWord)) matchCount--;
                            prev += wordLen;
                        }
                    }
                    if(matchCount == array.length){
                        res.add(prev);
                        String prevWord = s.substring(prev, prev + wordLen);
                        word.put(prevWord, word.get(prevWord) - 1);
                        matchCount--;
                        prev += wordLen;
                    }
                }
            }
        }
        return res;
    }
    private Map<String, Integer> wordCount(String[] array){
        HashMap<String, Integer> wordDict = new HashMap<>();
        for(String string:array){
            wordDict.putIfAbsent(string, 0);
            wordDict.put(string, wordDict.get(string) + 1);
        }
        return wordDict;
    }
}
