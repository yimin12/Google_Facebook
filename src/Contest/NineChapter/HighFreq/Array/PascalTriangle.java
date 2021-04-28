package Contest.NineChapter.HighFreq.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example
 * Example 1:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class PascalTriangle {


    public List<List<Integer>> generate(int numRows){
        List<List<Integer>> res = new ArrayList<>();
        if(numRows <= 0) return res;
        for(int i = 0; i < numRows; i ++){
            List<Integer> cur = new ArrayList<>();
            for(int j = 0; j <= i; j ++){
                if(j == 0 || j == i) cur.add(1);
                else {
                    int ans = res.get(i-1).get(j-1) + res.get(i-1).get(j);
                    cur.add(ans);
                }
            }
            res.add(cur);
        }
        return res;
    }

    /**
     * Description
     * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
     *
     * 1.Note that the row index starts from 0.
     * 2.In Pascal's triangle, each number is the sum of the two numbers directly above it.
     *
     * Example
     * Example1
     *
     * Input: 3
     * Output: [1,3,3,1]
     * Example2
     *
     * Input: 4
     * Output: [1,4,6,4,1]
     */
    public List<Integer> getRow(int rowIndex){
        List<Integer> res = new ArrayList<>();
        if(rowIndex <= 0) return res;
        res.add(1);
        for(int i = 1; i <= rowIndex; i ++){
            Integer pre = 1;
            for(int j = 0; j < i; j ++){
                if(j == i || j == 0) res.add(1);
                else {
                    Integer temp = res.get(j);
                    res.set(j, pre + res.get(j));
                    pre = temp;
                }
            }
        }
        return res;

    }
}
