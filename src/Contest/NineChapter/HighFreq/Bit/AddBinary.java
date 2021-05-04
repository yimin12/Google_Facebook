package Contest.NineChapter.HighFreq.Bit;

/**
 * Description
 * Given two binary strings, return their sum (In binary notation).
 *
 * Example
 * Example 1:
 *
 * Input:
 * a = "0", b = "0"
 * Output:
 * "0"
 * Example 2:
 *
 * Input:
 * a = "11", b = "1"
 * Output:
 * "100"
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        if(a == null || b == null) return "";
        if(a.length() < b.length()){
            String temp = a;
            a = b;
            b = temp;
        }
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        // start from shorter length first
        while(j >= 0){
            int sum = (a.charAt(i) - '0') + (b.charAt(j) - '0') + carry;
            sb = sb.append(sum & 1);
            carry = sum / 2;
            j --;
            i --;
        }

        while(i >= 0){
            int sum = (a.charAt(i) - '0') + carry;
            sb.append(sum & 1);
            carry = sum / 2;
            i --;
        }
        if(carry == 1){
            sb.append("1");
        }
        return sb.reverse().toString();
    }
}
