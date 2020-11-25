package Algorithm.Graph.Tree.SegmentTree;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/27 19:30
 *   @Description :
 *      Build a segment tree to maintain the largest value
 */
public class SegmentTree {

    class SegmentTreeNode{
        public int start, end, max;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int max){
            this.start = start;
            this.end = end;
            this.max = max;
            this.left = this.right = null;
        }
    }

    private SegmentTreeNode root;

    public SegmentTree(){
        this.root = null;
    }

    public SegmentTreeNode build(int[] A){
        return buildHelper(0, A.length - 1, A);
    }
    // recursive add
    public SegmentTreeNode buildHelper(int left, int right, int[] array){
        // base case
        if(left > right){
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(left, right, Integer.MIN_VALUE);
        if(left == right){
            return root;
        }
        int mid = left + (right - left)/2;
        root.left = buildHelper(left, mid, array);
        root.right = buildHelper(mid+1, right, array);
        root.max = Math.max(root.left.max, root.right.max);
        // root.min = Math.min(root.left.min, root.right.min); maintain the min property
       // root.sum = root.left.sum + root.right.sum; maintain the sum property
        return root;
    }

    // query max or return the property you want to maintain
    public int query(SegmentTreeNode root, int start, int end){
        // base case: within the range
        if(start <= root.start && root.end <= end){
            return root.max;
        }
        int mid = root.start + (root.end - root.start)/2;
        int res = Integer.MIN_VALUE;
        if(mid >= start){
            res = Math.max(res, query(root.left, start, end));
        }
        if(mid + 1 <= end){
            res = Math.max(res, query(root.right, start, end));
        }
        return res;
    }

    public void update(SegmentTreeNode root, int index, int value){
        if(root.start == root.end && root.start == index){
            root.max = value;
            return;
        }
        int mid = root.start + (root.end - root.start)/2;
        if(index <= mid){
            update(root.left, index, value);
            root.max = Math.max(root.right.max, root.left.max);
        } else {
            update(root.right, index, value);
            root.max =  Math.max(root.left.max, root.right.max);
        }
        return;
    }
}
