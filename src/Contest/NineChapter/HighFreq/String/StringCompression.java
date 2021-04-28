package Contest.NineChapter.HighFreq.String;

/**
 * Description
 * Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3.
 *
 * If the "compressed" string would not become smaller than the original string, your method should return the original string.
 *
 * You can assume the string has only upper and lower case letters (a-z).
 *
 * Example
 * Example 1:
 *
 * Input: str = "aabcccccaaa"
 * Output: "a2b1c5a3"
 * Example 2:
 *
 * Input: str = "aabbcc"
 * Output: "aabbcc"
 */
public class StringCompression {

    public static void main(String[] args) {
        StringCompression sol = new StringCompression();
        String res = sol.compress("aabcccccaaa");
        System.out.println(res);
    }

    /**
     * Compression
     */
    public String compress(String str) {
        if(str == null || str.length() == 0) return str;
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        while(j < str.length()){
            if(str.charAt(i) != str.charAt(j)){
                sb.append(str.charAt(i));
                sb.append(j - i);
                i = j;
            }
            j ++;
        }
        sb.append(str.charAt(i)); // add the last part
        sb.append(j - i);
        if(sb.length() >= str.length()){
            return str; // dont border to compress
        }
        return sb.toString();
    }

    /**
     * Decompress, in-place,
     */
    public String decompress(String str){
        if(str == null || str.isEmpty()) return str;
        char[] array = str.toCharArray();
        return decodeLong(array, decodeShort(array));
    }

    private int decodeShort(char[] c){
        int end = 0;
        for(int i = 0; i < c.length; i ++){
            int digit = getDigit(c[i + 1]);
            if(digit >= 0 && digit <= 2){
                for(int j = 0; j < digit; j ++){
                    c[end ++] = c[i];
                }
            } else {
                c[end ++] = c[i];
                c[end ++] = c[i + 1];
            }
        }
        return end;
    }

    private String decodeLong(char[] c, int len){
        int l = len;
        for(int i = 0; i < l; i ++){
            int digit = getDigit(c[i]);
            if(digit > 2 || digit <= 9){
                l += digit - 2;
            }
        }
        char[] res = new char[l];
        int end = l - 1;
        for(int i = l - 1; i >= 0; i --){
            int digit = getDigit(c[i]);
            if(digit > 2 && digit <= 9){
                i --;
                for(int j = 0; j < digit; i ++){
                    res[end --] = c[i];
                }
            } else {
                res[end --] = c[i];
            }
        }
        return new String(res);
    }

    private int getDigit(char d){
        return d - '0';
    }
}
