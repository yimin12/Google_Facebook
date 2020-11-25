package Algorithm.DynamicProgramming.LinearDP;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/7 20:13
 *      Description:
 *  	Given an array of 2D coordinates of points (all the coordinates are integers),
 * 	    find the largest number of points that can form a set such that any pair of points in
 * 	    the set can form a line with positive slope. Return the size of such a maximal set.
 *  Assumption:
 * 	    The given array is not null
 * 	    Node: if there does not even exist 2 points can form a line with positive slope, should return 0;
 *  Examples:
 * 	    <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are
 * 	    {<0, 0>, <1, 1>, <2, 3>}, the size is 3.
 */
public class LargestSetPointPositiveSlope {

    class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public int largestNumberPositiveSlope(Point[] points){
        // sorted in as near as the original point
        Arrays.sort(points, (a, b)->{
           if(a.y < b.y){
               return -1;
           } else if(a.y > b.y){
               return 1;
           } else if(a.x < b.x){
               return -1;
           } else if(a.x > b.x){
               return 1;
           }
           return 0;
        });
        int res = 0;
        // similar to longest ascending subsequence
        int[] longest = new int[points.length];
        for(int i = 0; i < longest.length; i++){
            for(int j = 0; j < i; j++){
                if(points[j].x < points[i].x && points[j].y < points[i].y){
                    longest[i] = Math.max(longest[i], longest[j]);
                }
            }
            longest[i]++;
            res = Math.max(res, longest[i]);
        }
        return res == 1 ? 0 : res;
    }
}
