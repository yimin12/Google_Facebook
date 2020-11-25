package Algorithm.BitManipulation;

import java.util.Arrays;
import java.util.HashMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 17:36
 *   @Description :
 *   Assumption:
 * 	    The words only contains character of 'a' to 'z'
 * 	    The dictionary is not null and does not contains null string, and has at least two strings
 * 	    If there is no such pair of words, just return 0
 *   Example:
 *      dictionary = ['abcde', 'abcd', 'ade', 'xy'], the largest product is 5 * 2 == 10 by choosing "abcde" and "xy"
 */
public class LargestWordsProduct {

    public int largestProduct(String[] dict){
        HashMap<String, Integer> bitMask = getBitMask(dict);
        Arrays.sort(dict, (a, b)-> a.length() - b.length());
        int largest = 0;
        for(int i = 1; i < dict.length; i++){
            for(int j = 0; j < i; j++){
                int cur = dict[i].length() * dict[j].length();
                if(cur < largest){
                    break;
                }
                int iMask = bitMask.get(dict[i]);
                int jMask = bitMask.get(dict[j]);
                if((iMask & jMask) == 0){
                    largest = cur;
                }
            }
        }
        return largest;
    }
    private HashMap<String, Integer> getBitMask(String[] dict){
        HashMap<String, Integer> map = new HashMap<>();
        for(String str:dict){
            int bitMask = 0;
            for(int i = 0; i < str.length(); i++){
                bitMask |= 1 << (str.charAt(i) - 'a');
            }
            map.put(str, bitMask);
        }
        return map;
    }
}
