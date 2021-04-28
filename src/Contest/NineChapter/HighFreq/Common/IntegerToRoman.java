package Contest.NineChapter.HighFreq.Common;

public class IntegerToRoman {

    public String intToRoman(int num){
        if(num <= 0) return "";
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        int digit = 0;
        while(num > 0){
            int times = num / nums[digit];
            num -= nums[digit] * times;
            for(; times > 0; times --){
                sb.append(symbols[digit]);
            }
            digit ++;
        }
        return sb.toString();
    }
}
