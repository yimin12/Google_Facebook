package Contest;

import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/14 13:11
 *   @Description :
 *      Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).
 *      Example 1:
        Input: points = [[0, 0], [0, 1], [1, 1], [1, 0]]
        Output:  true

        Explanation:
 */
public class ConvexPolygon {

    public boolean isConvex(List<List<Integer>> points){
        int cp, numPoints = points.size();
        boolean negativeFlag = false;
        boolean positiveFlag = false;
        for(int i = 2; i < numPoints + 2; i++){
            cp = Cross_Product(points.get((i - 2) % numPoints), points.get((i - 1) % numPoints), points.get((i % numPoints)));
            if(cp == 1){
                positiveFlag = true;
            } else if(cp == -1){
                negativeFlag = false;
            }
        }
        if(positiveFlag && negativeFlag || (!positiveFlag && negativeFlag)){
            return false;
        } else {
            return true;
        }
    }

    public int Cross_Product(List<Integer> p1, List<Integer> p2, List<Integer> p3){
        int ax = p2.get(0) - p1.get(0);
        int ay = p2.get(1) - p1.get(1);
        int bx = p3.get(0) - p2.get(0);
        int by = p3.get(1) - p2.get(1);
        int cp = ax * by - ay * bx;
        if(cp > 0){
            return 1;
        } else if(cp < 0){
            return -1;
        } else {
            return 0;
        }
    }
}
