package DataStructure.Design.SuffixArray;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/12 20:45
 *   @Description :
 *
 */
public class SuffixArrayMedium extends SuffixArray{

    // Wrapper class to help sort suffix ranks
    static class SuffixRankTuple implements Comparable<SuffixRankTuple>{

        int firstHalf, secondHalf, originalIndex;

        @Override
        public int compareTo(SuffixRankTuple o) {
            int cmp = Integer.compare(firstHalf, o.firstHalf);
            if(cmp == 0) Integer.compare(secondHalf, o.secondHalf);
            return cmp;
        }

        @Override
        public String toString() {
            return originalIndex + " -> (" + firstHalf + ", " + secondHalf + ")";
        }
    }

    public SuffixArrayMedium(String text) {
        super(toIntArray(text));
    }

    public SuffixArrayMedium(int[] text){
        super(text);
    }

    // Construct a suffix array in O(nlog^2(n)) time
    @Override
    protected void construct() {
        sa = new int[N];

        // Maintain suffix ranks in both a matrix with two rows containing the current and last rank information as well as some sortable rank objects
        int[][] suffixRanks = new int[2][N];
        SuffixRankTuple[] ranks = new SuffixRankTuple[N];

        // Assign a numerical value to each character in the text
        for(int i = 0; i < N; i++){
            suffixRanks[0][i] = T[i];
            ranks[i] = new SuffixRankTuple();
        }

        // O(logn(n))
        for(int pos = 1; pos < N; pos *= 2){
            for(int i = 0; i < N; i++){
                SuffixRankTuple suffixRank = ranks[i];
                suffixRank.firstHalf = suffixRanks[0][i];
                suffixRank.secondHalf = i + pos < N ? suffixRanks[0][i+pos] : - 1;
                suffixRank.originalIndex = i;
            }
            // O(nlog(n))
            Arrays.sort(ranks);
            int newRank = 0;
            suffixRanks[1][ranks[0].originalIndex] = 0;
            for(int i = 1; i < N; i++){
                SuffixRankTuple lastSuffixRank = ranks[i-1];
                SuffixRankTuple currSuffixRank = ranks[i];

                // If the first half differs from the second half
                if(currSuffixRank.firstHalf != lastSuffixRank.firstHalf || currSuffixRank.secondHalf != lastSuffixRank.secondHalf){
                    newRank++;
                }
                suffixRanks[i][currSuffixRank.originalIndex] = newRank;
            }

            // Place top row (current row) to be the last row
            suffixRanks[0] = suffixRanks[1];
            if(newRank == N - 1) break;
        }

        // Fill the suffix array
        for(int i = 0; i < N; i++){
            sa[i] = ranks[i].originalIndex;
            ranks[i] = null;
        }

        // Clean up
        suffixRanks[0] = suffixRanks[1] = null;
        suffixRanks = null;
        ranks = null;
    }

    public static void main(String[] args) {
        SuffixArrayMedium sa = new SuffixArrayMedium("ABBABAABAA");
        // SuffixArrayMed sa = new SuffixArrayMed("GAGAGAGAGAGAG");
        System.out.println(sa);
    }
}
