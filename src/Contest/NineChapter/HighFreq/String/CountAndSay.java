package Contest.NineChapter.HighFreq.String;

/**
 * Description
 * The count-and-say sequence is the sequence of integers beginning as follows:
 *
 * 1, 11, 21, 1211, 111221, ...
 *
 * 1 is read off as "one 1" or 11.
 *
 * 11 is read off as "two 1s" or 21.
 *
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n, generate the nth sequence.
 *
 * The sequence of integers will be represented as a string.
 *
 * Example
 * Example 1:
 *
 * Input: 1
 * Output: "1"
 * Example 2:
 *
 * Input: 5
 * Output: "111221"
 */
public class CountAndSay {

    public String countAndSay(int n) {
        // write your code here
        String old = "1";
        while(-- n > 0) {
            StringBuilder sb = new StringBuilder();
            char[] oldC = old.toCharArray();
            for(int i = 0; i < oldC.length; i ++){
                int count = 1;
                while((i + 1) < oldC.length && oldC[i] == oldC[i + 1]){
                    count ++;
                    i ++;
                }
                sb.append(String.valueOf(count) + String.valueOf(oldC[i]));
            }
            old = sb.toString();
        }
        return old;
    }
}