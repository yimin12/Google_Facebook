package Contest.NineChapter.Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Description
 * Each student has two attributes ID and scores. Find the average of the top five scores for each student.
 *
 * Example
 * Example 1:
 *
 * Input:
 * [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
 * Output:
 * 1: 72.40
 * 2: 97.40
 *
 * Example 2:
 *
 * Input:
 * [[1,90],[1,90],[1,90],[1,90],[1,90],[1,90]]
 * Output:
 * 1: 90.00
 */

public class HighFive {

    class Record {
        public int id, score;
        public Record(int id, int score){
            this.id = id;
            this.score = score;
        }
    }

    public Map<Integer, Double> highFive(Record[]  results){
        Map<Integer, Double> res = new HashMap<>();
        if(results == null || results.length == 0){
            return res;
        }
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for(Record r : results){
            if(!map.containsKey(r.id)){
                map.put(r.id, new PriorityQueue<>());
            }
            PriorityQueue<Integer> pq = map.get(r.id);
            if(pq.size() < 5){
                pq.offer(r.score);
            } else {
                if(pq.peek() < r.score){
                    pq.poll();
                    pq.offer(r.score);
                }
            }
        }

        for(Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()){
            int id = entry.getKey();
            PriorityQueue<Integer> scores = entry.getValue();
            double average = 0;
            for(int i = 0; i < 5; i ++){
                average += scores.poll();
            }
            average /= 5.0;
            res.put(id, average);
        }
        return res;
    }
}
