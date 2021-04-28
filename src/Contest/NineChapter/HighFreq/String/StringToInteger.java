package Contest.NineChapter.HighFreq.String;

/**
 * Description
 * Implement function atoi to convert a string to an integer.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 *
 * Example
 * Example 1:
 *
 * Input:
 *
 * string = "10"
 * Output:
 *
 * 10
 * Explanation:
 *
 * Convert a string to an integer.
 */
public class StringToInteger {

    public int atoi(String s) {
        boolean pos = true;
        char[] c = (s.trim() + " ").toCharArray();
        int i = 0;
        if(c[0] == '-'){
            pos = false;
            i ++;
        } else if(c[0] == '+'){
            pos = true;
            i ++;
        }
        long val = 0;
        while(i < c.length && Character.isDigit(c[i])){
            val = val * 10 + (c[i] - '0');
            i ++;
            if(val > Integer.MAX_VALUE){
                return pos ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }
        val = pos ? val : - val;
        return (int)val;
    }

}
