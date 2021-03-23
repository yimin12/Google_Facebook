package Contest.GodLeft.advanced;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 23:28
 *   @Description :
 *      给定一棵二叉树的头节点head，如果在某一个节点x上放置相机，那么x的父节点、x的所 有子节点以及x都可以被覆盖。返回如果要把所有数都覆盖，至少需要多少个相机。
 */
public class MinCameraCover {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
    }

    public static class ReturnType1{
        public long uncovered;
        public long coveredNoCamera;
        public long coveredHasCamera;

        public ReturnType1(long uncovered, long coveredNoCamera, long coveredHasCamera) {
            this.uncovered = uncovered;
            this.coveredNoCamera = coveredNoCamera;
            this.coveredHasCamera = coveredHasCamera;
        }
    }

    // method 1:
    public static int minCameraCover_1(Node root){
        ReturnType1 data = helper(root);
        return (int)Math.min(data.uncovered + 1, Math.min(data.coveredNoCamera, data.coveredHasCamera));
    }

    private static ReturnType1 helper(Node root){
        if(root == null){
            return new ReturnType1(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }
        ReturnType1 left = helper(root.left);
        ReturnType1 right = helper(root.right);
        // handle the problem while backtracking
        long uncovered = left.coveredNoCamera + right.coveredNoCamera;
        long coveredNoCamera = Math.min(left.coveredHasCamera+right.coveredHasCamera, Math.min(left.coveredHasCamera + right.coveredNoCamera, left.coveredNoCamera + right.coveredHasCamera));
        long coveredHasCamera = Math.min(left.uncovered, Math.min(left.coveredNoCamera, left.coveredHasCamera)) + Math.min(right.uncovered, Math.min(right.coveredHasCamera, right.coveredNoCamera)) + 1;
        return new ReturnType1(uncovered, coveredNoCamera, coveredHasCamera);
    }

    public enum Status{
        UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA
    }

    public static class ReturnType2{
        public Status status;
        public int cameras;

        public ReturnType2(Status status, int cameras) {
            this.status = status;
            this.cameras = cameras;
        }
    }

    // method 2: encapsulate it
    public static int minCameraCover_2(Node root){
        ReturnType2 data = processing(root);
        return data.cameras + (data.status == Status.UNCOVERED ? 1 : 0);
    }
    
    public static ReturnType2 processing(Node root){
        if(root == null){
            return new ReturnType2(Status.COVERED_NO_CAMERA, 0);
        }
        ReturnType2 left = processing(root.left);
        ReturnType2 right = processing(root.right);
        int cameras = left.cameras + right.cameras;
        if(left.status == Status.UNCOVERED || right.status == Status.UNCOVERED){
            return new ReturnType2(Status.COVERED_HAS_CAMERA, cameras + 1);
        }
        if(left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA){
            return new ReturnType2(Status.COVERED_NO_CAMERA, cameras);
        }
        return new ReturnType2(Status.UNCOVERED, cameras);
        
    }
}
