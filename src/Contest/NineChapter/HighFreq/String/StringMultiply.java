package Contest.NineChapter.HighFreq.String;

public class StringMultiply {

    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null) return null;
        int m = num1.length(), n = num2.length();
        int len = m + n;
        int i, j, product, carry;
        int[] num3 = new int[len];
        for(i = m - 1; i >= 0; i --){
            carry = 0;
            for(j = n - 1; j >= 0; j --){
                product = carry + num3[i + j + 1] + Character.getNumericValue(num1.charAt(i)) * Character.getNumericValue(num2.charAt(j));
                num3[i + j + 1] = product % 10;
                carry = product / 10;
            }
            num3[i + j + 1] = carry;
        }
        StringBuilder sb = new StringBuilder();
        i = 0;
        while(i < len - 1 && num3[i] == 0){
            i ++;
        }
        while(i < len){
            sb.append(num3[i ++]);
        }
        return sb.toString();
    }
}
