package Contest.NineChapter.HighFreq.Array;

import java.util.*;

/**
 * Description
 * Give a number of arrays, find their intersection, and output their intersection size.
 *
 * The total number of all array elements is not more than 500000.
 * There are no duplicated elements in each array.
 * Example
 * Example 1:
 *
 * 	Input:  [[1,2,3],[3,4,5],[3,9,10]]
 * 	Output:  1
 *
 * 	Explanation:
 * 	Only '3' in all three array.
 * Example 2:
 *
 * 	Input: [[1,2,3,4],[1,2,5,6,7][9,10,1,5,2,3]]
 * 	Output:  2
 *
 * 	Explanation:
 * 	The set is [1,2].
 */
public class IntersectionOfArray {

    /**
     * PriorityQueue Version
     */
    class Pair {
        public int row, col;
        public Pair(int row,  int col){
            this.row = row;
            this.col = col;
        }
    }

    // merge k sort array -> O(nk(logn + logk)) sorting + filter by pq
    // space complexity O(k)
    public int intersectionOfArrays(int[][] arrays){
        Comparator<Pair> comparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return arrays[o1.row][o1.col] - arrays[o2.row][o2.col];
            }
        };
        PriorityQueue<Pair> queue = new PriorityQueue<>(arrays.length, comparator);
        for(int i = 0; i < arrays.length; i ++){
            if(arrays[i].length == 0){
                return 0;
            }
            Arrays.sort(arrays[i]);
            queue.offer(new Pair(i, 0));
        }
        int lastValue = 0, count = 0;
        int intersection = 0;
        // Ensure the assumption There are no duplicated elements in each array
        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            if(arrays[pair.row][pair.col] != lastValue || count == 0){
                if(count == arrays.length){
                    intersection ++;
                }
                lastValue = arrays[pair.row][pair.col];
                count = 1;
            } else {
                count ++;
            }
            pair.col ++;
            if(pair.col < arrays[pair.row].length){
                queue.offer(pair);
            }
        }
        if(count == arrays.length){
            intersection ++;
        }
        return intersection;
    }

    /**
     * HashMap Version for Time complexity : O(nk) and Space O(n);
     */
    public int intersectionOfArrayII(int[][] arrays){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < arrays.length; i ++){
            set.add(arrays[0][i]);
        }
        for(int i = 1; i < arrays.length; i ++){
            Set<Integer> shuffle = new HashSet<>();
            for(int j = 0; j < arrays[i].length; j ++){
                if(set.contains(arrays[i][j])){
                    shuffle.add(arrays[i][j]);
                }
            }
            set = shuffle;
        }
        return set.size();
    }
}
