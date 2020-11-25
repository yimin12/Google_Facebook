package Contest.TicTok;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/22 22:14
 *   @Description :
 *      A character transformation T(char1, char2) of a string is defined as converting all char 1's within that string into char2's. For example, T('t', 's') for
 *      String "tiktok" will result in "siksok" by replacing all letter 't' with letter 's'
 *
 *      Input: source = "aaa", target = "bbb"
 *      result: 1
 *      Explanation: only 1 transformation from 'a' to 'b'
 */
public class MinimumCharacterTransformation {

    private static boolean isCycle;

    public static int minDistance(String s1, String s2) {
        int res = 0;
        Map<Character, Character> changeToMap = new HashMap<>();
        Map<Character, Character> changeFromMap = new HashMap<>();
        Set<Character> drop = new HashSet<>(); // In case of casting a better choice
        for (int i = 0; i < s1.length(); i++) {
            char cS1 = s1.charAt(i);
            char cS2 = s2.charAt(i);
            if (cS1 == cS2) continue;
            if (changeToMap.containsKey(cS1) && changeToMap.get(cS1) != cS2) {
                return -1;
            }
            if (changeFromMap.containsKey(cS2) && changeFromMap.get(cS2) != cS1) {
                drop.add(changeFromMap.get(cS2));
                s1 = s1.replace(changeFromMap.get(cS2), cS1);
                changeToMap.remove(changeFromMap.get(cS2));
                res++;
            }
            changeToMap.put(cS1, cS2);
            changeFromMap.put(cS2, cS1);
        }
        boolean[] visited = new boolean[26];
        for (char c : changeToMap.keySet()) {
            if (!visited[c - 'a']) {
                visited[c - 'a'] = true;
                boolean[] localVisited = new boolean[26];
                isCycle = false;
                boolean flag = false;
                int elementCount = dfs(c, localVisited, visited, changeToMap);
                if (isCycle) {
                    for(Character character : drop){
                        if(localVisited[character-'a']) flag = true;
                    }
                    if(drop.isEmpty() || flag){
                        res += elementCount + 1;
                    } else {
                        res += elementCount;
                    }
                }
                else res += elementCount - 1;
            }
        }
        return res;
    }

    private static int dfs(char c, boolean[] localVisited, boolean[] visited, Map<Character, Character> map) {
        int count = 0;
        if (localVisited[c - 'a']) {
            isCycle = true;
        } else {
            localVisited[c - 'a'] = true;
            visited[c - 'a'] = true;
            if (map.containsKey(c)) {
                count = 1 + dfs(map.get(c), localVisited, visited, map);
            } else {
                count = 1;
            }
        }
        return count;

    }

    public static void main(String[] args) {
        System.out.println(minDistance("abcdefghijkmabcxyz","bcdefghijkmabcdbcd"));
//        System.out.println(minDistance("ababcc","cccccc"));
//        System.out.println(minDistance("aaa","bbb"));
//        System.out.println(minDistance("ab","ba"));
//        System.out.println(minDistance("abcad","dcbxa"));
//        System.out.println(minDistance("abcad","dcbda"));
    }

}
