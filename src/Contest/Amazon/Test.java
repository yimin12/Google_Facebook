package Contest.Amazon;

import java.util.HashSet;
import java.util.Set;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/16 21:30
 *   @Description :
 *
 */
public class Test {

    public static void main(String[] args) {
        int abc = countKDistinctSubstrings("abafg", 2);
        System.out.println(abc);
    }

    static int countKDistinctSubstrings(String s, int num)
    {
        if(num == 0) return 0;
        Set<String> visited = new HashSet<>();
        Set<Character> count = new HashSet<>();
        int res = 0;
        for(int right = 0, left = 0; right < s.length(); right ++){
            count.add(s.charAt(right));
            while(left < right && count.size() == num){
                if(count.size() == 2 && !visited.contains(s.substring(left, right))) {
                    res ++;
                    visited.add(s.substring(left, right));
                }
                count.remove(s.charAt(left ++));
            }
        }
        return res;
    }
}
