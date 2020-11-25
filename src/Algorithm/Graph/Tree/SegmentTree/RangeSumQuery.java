package Algorithm.Graph.Tree.SegmentTree;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 20:11
 *   @Description :
 *      Segment Tree, Binary Indexed Tree
        Medium
        Given an integer array nums, find the sum of the elements between indices i and j (i≤j), inclusive.
        The update(i, val) function modifies nums by updating the element at index i to val.
        Example:
        Given nums = [1, 3, 5]

        sumRange(0, 2) -> 9
        update(1, 2)
        sumRange(0, 2) -> 8
        Note:
        The array is only modifiable by the update function.
        You may assume the number of calls to update and sumRange function is distributed evenly.
 */
public class RangeSumQuery {

    class SegmentTreeNode{
        int start, end, sum;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int sum){
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = this.right = null;
        }
    }

    SegmentTreeNode root;

    public RangeSumQuery(int[] array){
        root = build(0, array.length - 1, array);
    }

    public SegmentTreeNode build(int start, int end, int[] array){
        if(start > end){
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
        if(start == end){
            root.sum = array[start];
        } else {
            int mid = start + (end - start)/2;
            root.left = build(start, mid, array);
            root.right = build(mid + 1, end, array);
            root.sum = root.left.sum + root.right.sum;
        }
        return root;
    }

    public int query(SegmentTreeNode root, int start, int end){
        if(root.start == start && root.end == end){
            return root.sum;
        }
        int mid = root.start + (root.end - root.start)/2;
        int leftSum = 0, rigntSum = 0;
        if(start <= mid){
            if(mid < end){
                leftSum = query(root.left, start, mid);
            } else {
                leftSum = query(root.left, start, end);
            }
        }
        if(mid < end){
            if(start <= mid){
                rigntSum = query(root.right, mid + 1, end);
            } else {
                rigntSum = query(root.right, start, end);
            }
        }
        return leftSum + rigntSum;
    }

    public void update(SegmentTreeNode root, int index, int value){
        if(root.start == index && root.end == index){
            root.sum = value;
            return;
        }
        int mid = root.start + (root.end - root.start)/2;
        if(index <= mid && root.start <= mid){
            update(root.left, index, value);
        } else if(index > mid & root.end >= mid){
            update(root.right, index, value);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    public static void main(String[] args) {
        System.out.println(Math.random());
    }
}
