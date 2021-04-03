package Contest.Bloomberg;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/31 14:34
 *   @Description :
 *
 */
public class FreqStack {

    Map<Integer, Integer> freq;
    Map<Integer, Deque<Integer>> m;
    int maxFreq = 0; // maintain the global max frequency

    public FreqStack() {
        freq = new HashMap<>();
        m = new HashMap<>();
    }

    public void push(int val) {
        int count = freq.getOrDefault(val, 0) + 1;
        freq.put(val, count);
        maxFreq = Math.max(maxFreq, count);
        m.putIfAbsent(count, new LinkedList<>());
        m.get(count).push(val);
    }

    public int pop() {
        int val = m.get(maxFreq).pop();
        freq.put(val, maxFreq - 1);
        if(m.get(maxFreq).size() == 0) maxFreq --;
        return val;
    }
}
