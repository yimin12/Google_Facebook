package Contest.NineChapter.HighFreq.String;

/**
 * Description
 * Given two identical-sized string array A, B and a string S. All substrings A appearing in S are replaced by B.(Notice: From left to right, it must be replaced if it can be replaced. If there are multiple alternatives, replace longer priorities. After the replacement of the characters can't be replaced again.)
 *
 * The size of each string array does not exceed 100, the total string length does not exceed 50000.
 * The lengths of A [i] and B [i] are equal.
 * The length of S does not exceed 50000.
 * All characters are lowercase letters.
 * We guarantee that the A array does not have the same string
 * Example
 * Example 1
 *
 * Input:
 * A = ["ab","aba"]
 * B = ["cc","ccc"]
 * S = "ababa"
 *
 * Output: "cccba"
 * Explanation: In accordance with the rules, the substring that can be replaced is "ab" or "aba". Since "aba" is longer, we replace "aba" with "ccc".
 */
public class StringReplace {

    private static final int SEED = 31;
    private static final int HASH_SIZE = 10000000;

    public String stringReplace(String[] a, String[] b, String s) {
        if (s == null || s.length() == 0 || a == null || a.length == 0)
            return s;

        long[] aHashCodes = getAHashCodes(a);
        long[] sPrefixHashCodes = getSPrefixHashCodes(s);
        long[] seedPowersHashCodes = getSeedPowersHashCodes(s.length());
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < s.length(); i++) {
            int maxLen = -1, replaceAIndex = 0;
            for (int j = 0; j < a.length; j++) {
                int len = a[j].length();
                if (i + len > s.length())
                    continue;

                long targetHashCode = sPrefixHashCodes[i + len] - sPrefixHashCodes[i]*seedPowersHashCodes[len]%HASH_SIZE;
                targetHashCode += targetHashCode < 0 ? HASH_SIZE : 0;

                if (targetHashCode == aHashCodes[j] && len > maxLen && a[j].equals(s.substring(i, i+len))) {
                    maxLen = len;
                    replaceAIndex = j;
                }
            }

            if (maxLen != -1) {
                sb.replace(i, i + maxLen, b[replaceAIndex]);
                i = i + maxLen - 1;
            }
        }

        return sb.toString();
    }

    private long[] getAHashCodes(String[] sources){
        if (sources == null)
            return new long[0];
        long[] res = new long[sources.length];

        for (int i = 0; i < sources.length; i++) {
            res[i] = getHashCode(sources[i]);
        }

        return res;
    }

    private long getHashCode(String s) {
        long res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = (res + s.charAt(i) - 'a') * SEED % HASH_SIZE;
        }
        return res;
    }

    /**
     *
     * @param
     * @return hash codes of first i characters of s
     *
     */
    private long[] getSPrefixHashCodes(String s){
        if (s == null)
            return new long[0];
        long[] res = new long[s.length()+1];
        res[0] = 0;

        for (int i = 1; i <= s.length(); i++) {
            res[i] = (res[i-1] + s.charAt(i-1) - 'a') * SEED % HASH_SIZE;
        }

        return res;
    }

    /**
     *
     * @param length
     * @return an array representing SEED^i
     */
    private long[] getSeedPowersHashCodes(int length){
        if (length == 0)
            return new long[0];

        long[] res = new long[length];
        res[0] = 1;
        for (int i = 1; i < length; i++) {
            res[i] = res[i-1] * SEED % HASH_SIZE;
        }

        return res;
    }
}
