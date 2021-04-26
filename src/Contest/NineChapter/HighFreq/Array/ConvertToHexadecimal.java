package Contest.NineChapter.HighFreq.Array;

public class ConvertToHexadecimal {

    public String toHex(int num){
        if(num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        int len = 0;
        while(num != 0 && len < 8){
            int bit = num & 15; // MOD 16
            if(bit < 10){
                sb.append((char)('0' + bit));
            } else {
                sb.append((char)('a' + bit - 10));
            }
            num >>= 4;
            len ++;
        }
        return sb.reverse().toString();
    }
}
