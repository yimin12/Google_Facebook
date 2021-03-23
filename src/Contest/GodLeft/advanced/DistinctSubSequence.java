package Contest.GodLeft.advanced;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 1:11
 *   @Description :
 *
 */
public class DistinctSubSequence {

    public static int distinctSubSequence_1(String s){
        char[] str = s.toCharArray();
        int res = 0;
        int f[] = new int[str.length];
        Arrays.fill(f, 1);
        for(int i = 0; i < str.length; i ++){
            for(int j = 0; j < i; j ++){
                if(str[i] != str[j]){
                    f[i] += f[j];
                }
            }
            res += f[i];
        }
        return res;
    }
}
