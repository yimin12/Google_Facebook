package DataStructure.Design.Tree;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/23 23:20
 *   @Description :
 *
 */
public class SizeBalancedTree {

    public static class SBTNode {
        public long key;
        public SBTNode l;
        public SBTNode r;
        public long size;
        public long all;

        public SBTNode(long k) {
            key = k;
            size = 1;
            all = 1;
        }
    }

    public static class SizeBalancedTreeSet {
        private SBTNode root;

        private SBTNode rightRotate(SBTNode cur) {
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTNode leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            // all modify
            leftNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
            return leftNode;
        }

        private SBTNode leftRotate(SBTNode cur) {
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            // all modify
            rightNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
            return rightNode;
        }

        private SBTNode matain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            if (cur.l != null && cur.l.l != null && cur.r != null && cur.l.l.size > cur.r.size) {
                cur = rightRotate(cur);
                cur.r = matain(cur.r);
                cur = matain(cur);
            } else if (cur.l != null && cur.l.r != null && cur.r != null && cur.l.r.size > cur.r.size) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = matain(cur.l);
                cur.r = matain(cur.r);
                cur = matain(cur);
            } else if (cur.r != null && cur.r.r != null && cur.l != null && cur.r.r.size > cur.l.size) {
                cur = leftRotate(cur);
                cur.l = matain(cur.l);
                cur = matain(cur);
            } else if (cur.r != null && cur.r.l != null && cur.l != null && cur.r.l.size > cur.l.size) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = matain(cur.l);
                cur.r = matain(cur.r);
                cur = matain(cur);
            }
            return cur;
        }

        private SBTNode add(SBTNode cur, long key) {
            if (cur == null) {
                return new SBTNode(key);
            } else {
                cur.all++;
                if (key == cur.key) {
                    return cur;
                } else {
                    cur.size++;
                    if (key < cur.key) {
                        cur.l = add(cur.l, key);
                    } else {
                        cur.r = add(cur.r, key);
                    }
                    return matain(cur);
                }
            }
        }

        public void add(long sum) {
            root = add(root, sum);
        }

        public long lessKeySize(long key) {
            SBTNode cur = root;
            long ans = 0;
            while (cur != null) {
                if (key == cur.key) {
                    return ans + (cur.l != null ? cur.l.all : 0);
                } else if (key < cur.key) {
                    cur = cur.l;
                } else {
                    ans += cur.all - (cur.r != null ? cur.r.all : 0);
                    cur = cur.r;
                }
            }
            return ans;
        }

        public long moreKeySize(long key) {
            return root != null ? (root.all - lessKeySize(key + 1)) : 0;
        }

    }
}
