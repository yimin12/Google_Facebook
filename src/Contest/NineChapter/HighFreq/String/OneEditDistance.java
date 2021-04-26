package Contest.NineChapter.HighFreq.String;

/**
 * Description
 * Given two strings S and T, determine if they are both one edit distance apart.
 * One ediit distance means doing one of these operation:
 *
 * insert one character in any position of S
 * delete one character in S
 * change one character in S to other character
 * Example
 * Example 1:
 *
 * Input: s = "aDb", t = "adb"
 * Output: true
 * Example 2:
 *
 * Input: s = "ab", t = "ab"
 * Output: false
 * Explanation:
 * s=t ,so they aren't one edit distance apart
 */
public class OneEditDistance {

    public boolean isOneEditdistance(String s, String t){
        if(s == null || t == null){
            return s == t;
        }
        if(s.equals(t)) return false;
        int n = s.length(), m = t.length(), len = Math.min(n, m), index = 0;
        if(Math.abs(m-n) > 1) return false;
        for(int i = 0; i < len; i ++){
            index = i + 1;
            if(s.charAt(i) != t.charAt(i)){
                return s.substring(index).equals(t.substring(index)) // replace
                        || s.substring(index).equals(t.substring(index - 1)) // delete
                        || s.substring(index - 1).equals(t.substring(index)); // add
            }
        }
        return true;
    }
}
