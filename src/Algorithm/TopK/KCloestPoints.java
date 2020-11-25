package Algorithm.TopK;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/3 18:41
 *   @Description :
 *       Given some points and an origin point in two-dimensional space, find k points which are nearest to the origin.
 *       Return these points sorted by distance, if they are same in distance, sorted by the x-axis, and if they are same in the x-axis, sorted by y-axis.
 *       Input: points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
 *       Output: [[1,1],[2,5],[4,4]]
 */
public class KCloestPoints {

    class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    private Point global_origin = null;
    // Base on the priorityQueue and do the inner sort, Time: O(nlogk + k)
    public Point[] kClosest(Point[] points, Point origin, int k){
        global_origin = origin;
        // Max Heap
        PriorityQueue<Point> pq = new PriorityQueue<Point>(k, new Comparator<Point>(){
            @Override
            public int compare(Point o1, Point o2) {
                int dif = getDistance(o1, origin) - getDistance(o2, origin);
                if(dif != 0){
                   return -dif;
                } else {
                    return o2.x - o1.x;
                }
            }
        });
        for(int i = 0; i < points.length; i++){
            pq.offer(points[i]);
            if(pq.size() > k){
                pq.poll();
            }
        }
        Point[] result = new Point[k];
        while(k >= 0){
            result[--k] = pq.poll();
        }
        return result;
    }

    // Use heapify and pop the first k value, O(n + klogn)
    private Point origin;
    private int size;

    public Point[] kClosestII(Point[] points, Point origin, int k){
        if(points == null || points.length == 0 || k <= 0){
            return new Point[0];
        }
        this.origin = origin;
        // Use O(n) to sort the value
        heapify(points);
        Point[] results = new Point[k];
        for(int i = 0; i < k; i++){
            results[i] = pop(points);
        }
        return results;
    }
    // helper function of heapify
    private void heapify(Point[] points){
        size = points.length;
        for(int i = size/2; i >= 0; i--){
            procolateDown(points, i);
        }
    }
    private void procolateDown(Point[] points, int index) {
        while (index < size) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int min = index;
            if (left < size && compare(points[min], points[left]) > 0) {
                min = left;
            }
            if (right < size && compare(points[min], points[right]) > 0) {
                min = right;
            }
            if (index != min) {
                Point temp = points[index];
                points[index] = points[min];
                points[min] = temp;
            } else {
                break;
            }
        }
    }
    // first sorted by their distance away from the origin, then the smaller x, and then the smaller y will go first
    private int compare(Point a, Point b){
        int distance = getDistance(a, origin) - getDistance(b, origin);
        // case 1: different distance away from the origin
        if(distance != 0){
            return distance;
        }
        // case 2: same distance away from the origin
        if(a.x != b.x){
            return a.x - b.x;
        }
        return a.y - b.y;
    }
    // every time pop will reorganized the simulated heap
    private Point pop(Point[] points){
        Point top = points[0];
        points[0] = points[size - 1];
        this.size--;
        procolateDown(points, 0);
        return top;
    }


    private int getDistance(Point a, Point o){
        return (a.x - o.x)*(a.x - o.x) + (a.y - o.y)*(a.y - o.y);
    }


    public static void main(String[] args) {

    }
}
