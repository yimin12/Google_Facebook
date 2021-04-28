package Contest.NineChapter.HighFreq.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 * Example
 * Example1
 *
 * Input: "A"
 * Output: []
 * Example2
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class RepeatDNASequence {

    /**
     * We can use the ASCII code of  A: 0100 0001　　C: 0100 0011　　G: 0100 0111　　T: 0100 0100
     * Notice that the last three digit are different, so 10-letter of DNA sequence can be represented by 30 bits
     * We can use rolling hash in this problem
     * @param s
     * @return
     */
    // O(n) time, O(n) Space
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() <= 10){
            return  res;
        }
        int mask = (1 << 27) - 1; // 27 consecutive ones
        int cur = 0;
        for(int i = 0; i < 9;i ++){
            cur = (cur << 3) | (s.charAt(i) & 7); // use 3 bit to identified A-C-G-T, MOD 8 -> & 7
        }
        int n = s.length();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 9; i < n; i ++){
            cur = ((cur & mask) << 3 | (s.charAt(i) & 7)); // cur mod 27 and right shift 3 position then or s.charAt(i) mod 8
            Integer count = map.get(cur);
            if(count == null){
                map.put(cur, 1);
            } else {
                map.put(cur, count + 1);
                if(count == 1){
                    res.add(s.substring(i - 9, i + 1));
                }
            }
        }
        return res;
    }
}
