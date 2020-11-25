package Algorithm.DynamicProgramming.LinearDP;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/5 0:14
 *   @Description :
 *    	Given three strings A, B and C. Determine if C can be created by merging
 * 	    A and B in a way that maintains the relative order of the characters in A and B.
 *  Assumption:
 * 	    none of A,B,C is null
 *  Examples:
 * 	    C = "abcde", A = "acd", B = "be", return true
 * 	    C = "abcde", A = "adc", B = "be", return false
 */
public class InterleaveStrings {

    public boolean canMerge(String a, String b, String c){
        int alen = a.length();
        int blen = b.length();
        int clen = c.length();
        if(alen + blen != clen){
            return false;
        }

        boolean[][] canMerge = new boolean[alen+1][blen+1];
        // whether the first ith chars of a and jth chars of b can merge to first (i+j)th of c
        for(int i = 0; i <= alen; i++){
            for(int j = 0; j <= blen; j++){
                if(i == 0 && j == 0){
                    // base case
                    canMerge[i][j] = true;
                }
                if(i > 0 && a.charAt(i - 1) == c.charAt(i+j - 1)){
                    canMerge[i][j] |= canMerge[i-1][j]; // the ith char of a matched!
                }
                if(j > 0  && a.charAt(j - 1) == c.charAt(i+j - 1)){
                    canMerge[i][j] |= canMerge[i][j-1];
                }
            }
        }
        return canMerge[alen][blen];
    }
}
