package Algorithm.TopK;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/3 21:48
 *   @Description :
 *
 */
public class TopKFrequentWords {

    // As for map reduce, there totally two stages
    // step 1: map
    public static class Map{
        public void map(String _, Document value, OutputCollector<String, Integer> output){
            // Output the results into the output buffer
            int id = value.id;
            String content = value.content;
            String[] words = content.split(" ");
            for(String word : words){
                if(word.length() > 0){
                    output.collect(word, 1);
                }
            }
        }
    }

    // step 2: reduce
    public static class Reduce{
        private PriorityQueue<Pair> pq = null;
        private int k; // top k frequent word
        private Comparator<Pair> pairComparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.value != o2.value){
                    return o1.value - o2.value;
                }
                return o2.key.compareTo(o1.key);
            }
        };
        // use public method rather than constructor
        public void setup(int k){
            this.k = k;
            this.pq = new PriorityQueue<>(k, pairComparator);
        }
        public void reduce(String key, Iterator<Integer> values){
            // aggregate the same word, the same word will be listed in sequence
            int sum = 0;
            while(values.hasNext()){
                sum += values.next();
            }
            Pair pair = new Pair(key, sum);
            if(pq.size() < k){
                pq.add(pair);
            } else {
                // if the pair is qualified to get into the queue of top k frequent
                Pair top = pq.peek();
                if(pairComparator.compare(pair, top) > 0){
                    pq.poll();
                    pq.offer(pair);
                }
            }
        }
        public void cleanup(OutputCollector<String, Integer> output){
            List<Pair> pairs = new ArrayList<>();
            while(!pq.isEmpty()){
                pairs.add(pq.poll());
            }
            // reverse the result and output
            int n = pairs.size();
            for(int i = n - 1; i >= 0; i--){
                Pair pair = pairs.get(i);
                output.collect(pair.key, pair.value);
            }
        }
    }
}


// class for map reduce
class Pair{
    String key;
    int value;
    Pair(String key, int value){
        this.key = key;
        this.value = value;
    }
}
// Output format for map reduce
class OutputCollector<K, V>{
    public void collect(K key, V value){
        return;
    }
}
class Document{
    public int id;
    public String content;
}

