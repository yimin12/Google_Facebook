package Contest.TicTok;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/22 21:08
 *   @Description :
 *      Given two arrays containing only unsorted integers, find the common elements (each number can be only used once, may contain duplicate)
 */
public class CommonElements {

    public List<Integer> findCommon(int[] a, int[] b){
        List<Integer> res = new ArrayList<>();
        if(a == null || b == null || a.length * b.length == 0){
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < a.length; i++){
            map.put(a[i], map.getOrDefault(a[i],0) + 1);
        }
        for(int j = 0; j < b.length; j++){
            if(map.containsKey(b[j])){
                res.add(b[j]);
                int count = map.get(b[j]);
                if(count == 1){
                    map.remove(b[j]);
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        CommonElements solution = new CommonElements();
        int[] a = {3,1,3,4,5,5,4}, b = {4,5,3,3,4};
        List<Integer> res = solution.findCommon(a, b);
        System.out.println(res.toString());

    }
}
