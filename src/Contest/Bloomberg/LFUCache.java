package Contest.Bloomberg;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/31 14:03
 *   @Description :
 *
 */
public class LFUCache {


    Map<Integer, Integer> values; // record the input
    Map<Integer, Integer> freq; // record the frequency of current key
    Map<Integer, LinkedHashSet<Integer>> lru; // key : insertion times, value: the insertion sequence
    final int limit;
    int min = - 1; // the minimum frequency of lfu in current time

    public LFUCache(int capacity) {
        this.limit = capacity;
        values = new HashMap<>();
        freq = new HashMap<>();
        lru = new HashMap<>();
        lru.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if(!values.containsKey(key)){
            return -1;
        }
        int count = freq.get(key);
        freq.put(key, count + 1);
        lru.get(count).remove(key); // evict from current frequency lru, and add it to next frequency
        if(count == min && lru.get(count).size() == 0){
            min ++;
        }
        if(!lru.containsKey(count + 1)){
            lru.put(count + 1, new LinkedHashSet<>());
        }
        lru.get(count + 1).add(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if(limit == 0){
            return;
        }
        if(values.containsKey(key)){
            values.put(key, value);
            get(key); // update the frequency and corresponding position
            return;
        }
        // case 1: evict
        if(values.size() >= limit){
            int evit = lru.get(min).iterator().next(); // remove the earliest node in lru map
            lru.get(min).remove(evit);
            values.remove(evit);
        }
        values.put(key, value);
        freq.put(key, 1);
        min = 1; // reset to 1;
        lru.get(1).add(key);
    }
}
