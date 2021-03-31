package Contest.Bloomberg;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/30 16:54
 *   @Description :
 *
 */
public class IntegerToRoman {

    public String intToRoman(int num) {
        if(num < 0) return "";
        int[] value = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] key = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < value.length && num > 0; i ++){
            while(value[i] <= num){ // 2000 == MM, greedy, use the largest available first
                num -= value[i];
                sb.append(key[i]);
            }
        }
        return sb.toString();
    }
}
