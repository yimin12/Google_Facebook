package Algorithm.Iterator;

import java.util.Iterator;
import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/3 19:19
 *   @Description :
 *      Design and implement an iterator to flatten a 2d vector. It should support the following operations:nextandhasNext.
        Example:
        Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

        iterator.next(); // return 1
        iterator.next(); // return 2
        iterator.next(); // return 3
        iterator.hasNext(); // return true
        iterator.hasNext(); // return true
        iterator.next(); // return 4
        iterator.hasNext(); // return false
        Notes:
        Please remember to RESET your class variables declared in Vector2D, as static/class variables are persisted across multiple test cases . Please see here for more details.
        You may assume that next()call will always be valid, that is, there will be at least a next element in the 2d vector when
        next() is called.
 */
public class Flatten2DVector {

    // matrix version
    private int i, j;
    private int[][] v;
    public Flatten2DVector(int[][] matrix){
        i = 0;
        j = 0;
        v = matrix;
    }
    public int next(){
        int n = 0;
        if(i < v.length && j >= v[i].length){
            // find which row does it belongs to.
            while(i < v.length){
                i++;
                if(j < v[i].length){
                    break;
                }
            }
        }
        n = v[i][j];
        if(j == v[i].length - 1){
            j = 0;
            i++;
        } else {
            j++;
        }
        return n;
    }
    public boolean hasNext(){
        int ti = i;
        int tj = j;
        if(ti < v.length && tj < v[ti].length){
            return true;
        } else if(ti < v.length){
            while(ti < v.length){
                if(tj < v[ti].length){
                    return true;
                }
                ti++;
            }
        }
        return true;
    }
    private Iterator<List<Integer>> row;
    private Iterator<Integer> col;

    public Flatten2DVector(List<List<Integer>> nestedList){
        row = nestedList.iterator();
    }

    public int nextI(){
        hasNextI();
        return col.next();
    }
    public boolean hasNextI(){
        while((col==null||!col.hasNext()&& row.hasNext())){
            col = row.next().iterator();
        }
        return col != null && col.hasNext();
    }

}
