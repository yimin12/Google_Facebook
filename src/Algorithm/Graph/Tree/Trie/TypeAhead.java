package Algorithm.Graph.Tree.Trie;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 12:18
 *   @Description :
 *      Implement typeahead. Given a string and a dictionary, return all words that contains the string as a substring. The dictionary will give at the initialize method and wont be changed. The method to find all words with given substring would be called multiple times.
        Example
        Given dictionary ={"Jason Zhang", "James Yu", "Bob Zhang", "Larry Shi"}
        search"Zhang", return["Jason Zhang", "Bob Zhang"].
        search"James", return["James Yu"].
 */
public class TypeAhead {
    private Map<String, List<String>> map = new HashMap<String, List<String>>();
    public TypeAhead(Set<String> dict){
        for(String string : dict){
            int len = string.length();
            for(int i = 0; i < len; i++){
                for(int j = i + 1; j <= len; j++){
                    String temp = string.substring(i, j);
                    if(!map.containsKey(temp)){
                        map.put(temp, new ArrayList<>());
                        map.get(temp).add(string);
                    } else {
                        List<String> index = map.get(temp);
                        if(!string.equals(index.get(index.size() - 1))){
                            index.add(string);
                        }
                    }
                }
            }
        }
    }
    public List<String> search(String string){
        if(!map.containsKey(string)){
            return new ArrayList<>();
        } else {
            return map.get(string);
        }
    }
}
