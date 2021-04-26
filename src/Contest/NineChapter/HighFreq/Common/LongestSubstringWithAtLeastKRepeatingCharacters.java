package Contest.NineChapter.HighFreq.Common;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 *
 * Example
 * Example 1:
 *
 * Input:
 * s = "aaabb", k = 3
 * Output:
 * 3
 * Explanation:
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input:
 * s = "ababbc", k = 2
 * Output:
 * 5
 * Explanation:
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {


    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        if (k <= 1) return s.length();
        if (s.length() < k) return 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            char charAtI = s.charAt(i);
            if (!map.containsKey(charAtI)) {
                map.put(charAtI, 1);
            } else {
                map.put(charAtI, map.get(charAtI) + 1);
            }
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) < k) {
                sb.setCharAt(i, ',');
            }
        }
        String[] strings = sb.toString().split(",");
        if (strings.length == 1) return strings[0].length();
        int longest = 0;
        for (String st: strings) {
            longest = Math.max(longest, longestSubstring(st, k));
        }
        return longest;
    }
}

