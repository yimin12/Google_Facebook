package Algorithm.DynamicProgramming.LinearDP;

import java.util.HashSet;
import java.util.Set;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/4 22:14
 *   @Description :
 *  	Given a word and a dictionary, determine if it can be composed by concatenating words
 * 	    from the given dictionary.
 *  Assumption:
 * 	    The given word is not null and is not empty
 * 	    The given word is not null is not empty and all the words in the dictionary are not null or empty
 *  Examples:
 * 	    Dictionary: {��bob��, ��cat��, ��rob��}

Word: ��robob�� return false

Word: ��robcatbob�� return true since it can be composed by "rob", "cat", "bob"
 */
public class DictionaryWord {

    public boolean canBreak(String input, String[] dict){
        Set<String> dictSet = buildSet(dict);
        boolean[] canbreak = new boolean[input.length() + 1];
        canbreak[0] = true;
        for(int i = 1; i < input.length(); i++){
            for(int j = 0; j < i; j++){
                if(dictSet.contains(input.substring(j, i)) && canbreak[j]){
                    canbreak[i] = true;
                    break;
                }
            }
        }
        return canbreak[input.length()];
    }
    private Set<String> buildSet(String[] dict){
        Set<String> set = new HashSet<>();
        for(String str:dict){
            set.add(str);
        }
        return set;
    }
}
