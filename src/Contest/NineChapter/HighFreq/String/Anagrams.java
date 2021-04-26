package Contest.NineChapter.HighFreq.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 * Given an array of strings, return all groups of strings that are anagrams.If a string is Anagram,there must be another string with the same letter set but different order in S.
 *
 * All inputs will be in lower-case
 *
 * Example
 * Example 1:
 *
 * Input:["lint", "intl", "inlt", "code"]
 * Output:["lint", "inlt", "intl"]
 * Example 2:
 *
 * Input:["ab", "ba", "cd", "dc", "e"]
 * Output: ["ab", "ba", "cd", "dc"]
 */
public class Anagrams {

    public List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<>();
        if(strs == null || strs.length == 0) return res;
        Map<Long, List<String>> map = new HashMap<>();
        for(String s : strs){
            Long hash = getHash(s);
            map.putIfAbsent(hash, new ArrayList<>());
            map.get(hash).add(s);
        }
        for(Map.Entry<Long, List<String>> entry : map.entrySet()){
            if(entry.getValue().size() > 1){
                res.addAll(entry.getValue());
            }
        }
        return res;
    }

    private Long getHash(String s){
        long res = 0;
        int[] dict = new int[26];
        for(char c : s.toCharArray()){
            dict[c - 'a'] ++;
        }
        for(int i : dict){
            res += res * 26 + i;
        }
        return res;
    }
}
