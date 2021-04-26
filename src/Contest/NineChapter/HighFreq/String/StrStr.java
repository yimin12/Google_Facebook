package Contest.NineChapter.HighFreq.String;

/**
 * Description
 * For a given source string and a target string, you should output the first index(from 0) of target string in the source string.If the target does not exist in source, just return -1.
 *
 * Do I need to implement KMP Algorithm in a real interview?
 *
 * Not necessary. When you meet this problem in a real interview, the interviewer may just want to test your basic implementation ability. But make sure you confirm how to implement with the interviewer first.
 * Example
 * Example 1:
 *
 * Input:
 *
 * source = "source"
 * target = "target"
 * Output:
 *
 * -1
 */
public class StrStr {

    /**
     * Rabin-karp, AKA rolling hash
     */
    public static final int BASE = 10000007;

    public int strStrI(String source, String target){
        if (source == null || target == null) {
            return -1;
        }
        int m = target.length();
        if (m == 0) {
            return 0;
        }
        int pow = 1;
        for (int i = 0; i < m - 1; i++) {
            pow = (pow * 31) % BASE;
        }
        // target hash code
        int targetCode = 0;
        for (int i = 0; i < m; i++) {
            targetCode = (targetCode * 31 + target.charAt(i)) % BASE;
        }
        // abc
        // abcd
        // bcd
        int hashCode = 0;
        for (int i = 0; i < source.length(); i++) {
            hashCode = (hashCode * 31 + source.charAt(i)) % BASE;
            // 还没达到 m 的长度
            if (i < m - 1) {
                continue;
            }
            if (hashCode == targetCode) {
                if (target.equals(source.substring(i - m + 1, i + 1))) {
                    return i - m + 1;
                }
            }
            hashCode = hashCode - (pow * source.charAt(i - m + 1)) % BASE;
            if (hashCode < 0) {
                hashCode += BASE;
            }
        }
        return -1;
    }
}
