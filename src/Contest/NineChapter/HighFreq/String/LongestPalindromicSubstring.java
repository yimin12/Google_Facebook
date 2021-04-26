package Contest.NineChapter.HighFreq.String;

import java.util.Arrays;

/**
 * Description
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 *
 * Example
 * Example 1:
 *
 * Input:"abcdzdcab"
 * Output:"cdzdc"
 * Example 2:
 *
 * Input:"aba"
 * Output:"aba"
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring sol = new LongestPalindromicSubstring();
        String res = sol.longestPalindrome("aaaaa");
        System.out.println(res);
    }

    // Manacher Algorithm
    public String longestPalindrome(String s){
        if(s == null || s.length() == 0) return "";
        String ss = getModifiedString(s, s.length());
        int[] res = manacherAlgorithm(ss);
        return s.substring(res[0], res[0] + res[1]);
    }

    private int[] manacherAlgorithm(String s){
        int len = s.length();
        int[] P = new int[len];
        int c = 0; //stores the center of the longest palindromic substring until now
        int r = 0; //stores the right boundary of the longest palindromic substring until now
        int maxLen = 0, s_p = 0;
        for(int i = 0; i < len; i ++){
            //get mirror index of i
            int mirror = (2 * c) - i;

            //see if the mirror of i is expanding beyond the left boundary of current longest palindrome at center c
            //if it is, then take r - i as P[i]
            //else take P[mirror] as P[i]
            if(i < r) {
                P[i] = Math.min(r - i, P[mirror]);
            }
            //expand at i
            int a = i + (1 + P[i]);
            int b = i - (1 + P[i]);
            while(a < len && b >= 0 && s.charAt(a) == s.charAt(b)) {
                P[i]++;
                a++;
                b--;
            }
            if(i + P[i] > r){
                c = i;
                r = i + P[i];
                if(P[i] > maxLen){
                    maxLen = P[i];
                    s_p = i / 2 - P[i] / 2;
                }
            }
        }
        return new int[] {s_p, maxLen};
    }

    String getModifiedString(String s, int N){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i ++){
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString();
    }

    /**
     * Common way, expand from middle, O(n^2) Time, O(1) Space
     */
    public String longestPalindromeII(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int start = 0, len = 0, longest = 0;
        for (int i = 0; i < s.length(); i++) {
            len = findLongestPalindromeFrom(s, i, i);
            if (len > longest) {
                longest = len;
                start = i - len / 2;
            }

            len = findLongestPalindromeFrom(s, i, i + 1);
            if (len > longest) {
                longest = len;
                start = i - len / 2 + 1;
            }
        }

        return s.substring(start, start + longest);
    }

    private int findLongestPalindromeFrom(String s, int left, int right) {
        int len = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            len += left == right ? 1 : 2;
            left--;
            right++;
        }

        return len;
    }

}
