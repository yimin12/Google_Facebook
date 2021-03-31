package Contest;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/30 20:35
 *   @Description :
 *
 */
public class ZigZagPrinting {

    // Time : O(n), Space : O(n)
    public String convert(String s, int numRows) {
        if(s == null || s.length() == 0) return "";
        char[] c = s.toCharArray();
        int m = c.length;
        StringBuilder[] sb = new StringBuilder[m];
        int i, idx;
        for(i = 0; i < m; i ++){
            sb[i] = new StringBuilder();
        }
        i = 0;
        while(i < m){
            // handle the vertical line
            for(idx = 0; idx < numRows && i < m; idx ++){
                sb[idx].append(c[i++]);
            }
            // handle the diag line
            for(idx = numRows - 2; idx >= 1 && i < m; idx --){
                sb[idx].append(c[i++]);
            }
        }
        // merge all stringbuilder
        for(i = 1; i < m; i ++){
            sb[0].append(sb[i]);
        }
        return sb[0].toString();
    }
}
