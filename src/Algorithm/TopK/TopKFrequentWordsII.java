package Algorithm.TopK;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/3 22:56
 *   @Description :
 * Find top k frequent words in realtime data stream.
        Implement three methods for Topk Class:
        Algorithm.TopK(k). The constructor.
        add(word). Add a new word.
        topk(). Get the current top k frequent words.
    Input：
    Algorithm.TopK(2)
    add("lint")
    add("code")
    add("code")
    topk()
    Output：["code", "lint"]
    Explanation：
    "code" appears twice and "lint" appears once, they are the two most frequent words.
 */
public class TopKFrequentWordsII {

    // Good use Collections to sort the values of map, Another way is use TreeMap if the Element is not integer
    // In this example, it is a very good use of treeMap and TreeSet
    private Map<String, Integer> words = null;
    private NavigableSet<String> topK = null;
    private int k;

    // Implement self adjusted map for treeMap
    private Comparator<String> myComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            if(o1.equals(o2)){
                return 0;
            }
            int left = words.get(o1);
            int right = words.get(o2);
            if(left != right){
                return right - left;
            }
            // The value is the same but the string is not the same, we sort in alphabet sequence
            return o1.compareTo(o2);
        }
    };
    public TopKFrequentWordsII(int k){
        this.k = k;
        this.words = new HashMap<>();
        this.topK = new TreeSet(myComparator);
    }
    public void add(String word){
        // remove it and put it back
        if(words.containsKey(word)){
            if(topK.contains(word)){
                topK.remove(word);
            }
            words.put(word, words.get(word) + 1);
        } else {
            words.put(word, 1);
        }
        topK.add(word);
        if(topK.size() > k){
            topK.pollLast(); // treeMap can get rid of the smallest things
        }
    }
    public List<String> topk(){
        List<String> results = new ArrayList<>();
        Iterator it = topK.iterator();
        while(it.hasNext()){
            results.add((String)it.next());
        }
        return results;
    }
}
