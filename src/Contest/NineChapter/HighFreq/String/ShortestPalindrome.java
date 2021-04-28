package Contest.NineChapter.HighFreq.String;


public class ShortestPalindrome {

    public String shortestPalindrome(String s){
        if(s == null || s.length() == 0) return "";
        int j = 0;
        // find the start position that is not palindrome
        for(int i = s.length() - 1; i >= 0; i --){
            if(s.charAt(i) == s.charAt(j)){
                j += 1;
            }
        }
        if(j == s.length()) return s; // base case
        String suffix = s.substring(j);
        String prefix = new StringBuilder(suffix).reverse().toString();
        String mid = shortestPalindrome(s.substring(0, j));
        String res = prefix + mid + suffix;
        return res;
    }
}
