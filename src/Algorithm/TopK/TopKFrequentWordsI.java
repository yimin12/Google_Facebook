package Algorithm.TopK;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/3 22:56
 *   @Description : Given a list of words and an integer k, return the top k frequent words in the list.
 *          Input:
  [
    "yes", "lint", "code",
    "yes", "code", "baby",
    "you", "baby", "chrome",
    "safari", "lint", "code",
    "body", "lint", "code"
  ]
  k = 3
Output: ["code", "lint", "baby"]
 */
public class TopKFrequentWordsI {

    // Good use Collections to sort the values of map, Another way is use TreeMap if the Element is not integer
    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */
    public String[] topKFrequentWords(String[] words, int k) {
        // write your code here
        if (words == null || words.length == 0 || k < 1) {
            return new String[0];
        }

        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new MapComparator());

        String[] res = new String[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i).getKey();
        }
        return res;
    }

    private class MapComparator implements Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
            if (m1.getValue() == m2.getValue()) {
                return m1.getKey().compareTo(m2.getKey());
            }
            return m2.getValue() - m1.getValue();
        }
    }
}
