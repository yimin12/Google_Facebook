package Algorithm.DynamicProgramming.GambleDP;

import java.util.HashMap;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/10 23:17
 *   @Description :
 * 	    You are playing the following Flip Game with your friend: Given a string that contains only these two characters:+and-, you and your friend take
 * 	    turns to flip two consecutive"++"into"--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * 	    Write a function to determine if the starting player can guarantee a win.
 *  Example:
 *  	Input: s = "++++"
 * 	    Output: true
 * 	    Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 */
public class FlipGame {

    public boolean canWin(String s){
        if(s == null || s.length() == 0){
            return true;
        }
        HashMap<String, Boolean> map = new HashMap<>();
        return helper(s, map);
    }
    // recursion
    private boolean helper(String s, HashMap<String, Boolean> map){
        if(s == null || s.length() == 0) return false;
        if(map.containsKey(s)) return map.get(s);
        char[] array = s.toCharArray();
        boolean res = false;
        for(int i = 0; i < array.length - 1; i++){
            if(array[i] == '+' && array[i+1] == '+'){
                array[i] = array[i+1] = '-';
                boolean search = helper(new String(array), map);
                if(!search){
                    res = true;
                    break;
                }
                array[i] = array[i+1] = '+';
            }
        }
        map.put(s, res);
        return res;
    }
}
