package DataStructure.Design.Tree;

import DataStructure.Design.Utils.TreePrinter;
import DataStructure.Design.Utils.TreePrinter.PrintableNode;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/11 22:31
 *   @Description :
 *      <p>The Basic Concept of SplayTree is to keep frequently used nodes close to the root of the tree
 *      It performs basic operations such as insertion,search,delete,findMin,findMax in O(log n)
 *      amortized time Having frequently-used nodes near to the root can be useful in implementing many
 *      algorithms. e.g: Implementing caches, garbage collection algorithms etc Primary disadvantage of
 *      the splay tree can be the fact that its height can go linear. This causes the worst case running
 *      times to go O(n) However, the amortized costs of this worst case situation is logarithmic, O(logn)
 */
public class SplayTree<T extends Comparable<T>> {

    // SplayTree is not always balanced tree
    /**
     * six operations:
     *      1. zig  2.zag  3. zig-zig  4.zag-zag   5.zig-zag  6 zag-zig
     */
    private Node<T> root;

    // Construct splaytree
    public SplayTree(){
        this.root = null;
    }

    public SplayTree(Node<T> root){
        this.root = root;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /**
     * Searches a node and splays it on top, return the new root
     * @param
     */
    public Node<T> search(T node){
        if(root == null) return null;
        this.root = splayUtil(root, node);
        return this.root.getData().compareTo(node) == 0 ? this.root : null;
    }

    public Node<T> insert(T node){
        if(root == null){
            root = new Node<>(node);
            return root;
        }
        splay(node);
        ArrayList<Node<T>> l_r = split(node);
        Node<T> left = l_r.get(0);
        Node<T> right = l_r.get(1);

        root = new Node<>(node);
        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    public Node<T> delete(T node){
        if(root == null) return null;
        Node<T> searchResult = splay(node);
        if(searchResult.getData().compareTo(node) != 0) return null;
        Node<T> leftSubtree = root.getLeft();
        Node<T> rightSubtree = root.getRight();
        root.setLeft(null);
        root.setRight(null);
        root = join(leftSubtree, rightSubtree);
        return root;
    }

    /** Private Method for self-balanced tree **/
    private Node<T> rightRotate(Node<T> node){
        Node<T> p = node.getLeft();
        node.setLeft(p.getRight());
        p.setRight(node);
        return p;
    }

    private  Node<T> leftRotate(Node<T> node){
        Node<T> p = node.getRight();
        node.setRight(p.getLeft());
        p.setLeft(node);
        return p;
    }

    /** To FindMax Of Entire Tree * */
    public T findMax(){
        Node<T> temp = root;
        while(temp.getRight() != null) temp = temp.getRight();
        return temp.getData();
    }

    /** To FindMin Of Entire Tree * */
    public T findMin(){
        Node<T> temp = root;
        while(temp.getLeft() != null) temp = temp.getLeft();
        return temp.getData();
    }

    /** * To FindMax Of Tree with specified root * */
    public T findMax(Node<T> root){
        Node<T> temp = root;
        while(temp.getRight() != null) temp = temp.getRight();
        return temp.getData();
    }

    /** * To FindMin Of Tree with specified root * */
    public T findMin(Node<T> root){
        Node<T> temp = root;
        while(temp.getLeft() != null) temp = temp.getLeft();
        return temp.getData();
    }

    private Node<T> splayUtil(Node<T> root, T key){
        if(root == null || root.getData() == key) return root; // search the root
        if(root.getData().compareTo(key) > 0){
            if(root.getLeft() == null){
                return root;
            }
            if(root.getLeft().getData().compareTo(key) > 0){
                root.getLeft().setLeft(splayUtil(root.getLeft().getLeft(), key));
                root = rightRotate(root);
            } else if(root.getLeft().getData().compareTo(key) < 0){
                root.getLeft().setRight(splayUtil(root.getLeft().getRight(), key));
                if(root.getLeft().getRight() != null) root.setLeft(leftRotate(root.getLeft()));
            }
            return (root.getLeft() == null) ? root : rightRotate(root);
        } else {
            if(root.getRight() == null) return root;
            if(root.getRight().getData().compareTo(key) > 0){
                root.getRight().setLeft(splayUtil(root.getRight().getLeft(), key));
                if(root.getRight().getLeft() != null) root.setRight(rightRotate(root.getRight()));
            } else if(root.getRight().getData().compareTo(key) < 0) {
                // right-right
                root.getRight().setRight(splayUtil(root.getRight().getRight(), key));
                root = leftRotate(root);
            }
            return (root.getRight() == null) ? root : leftRotate(root);
        }
    }

    private Node<T> splay(T value){
        if(root == null) return null;
        this.root = splayUtil(root, value);
        return this.root;
    }

    private ArrayList<Node<T>> split(T value){
        Node<T> right;
        Node<T> left;
        if(value.compareTo(root.getData()) > 0){
            right = root.getRight();
            left = root;
            left.setRight(null);
        } else {
            left = root.getLeft();
            right = root;
            right.setLeft(null);
        }
        ArrayList<Node<T>> l_r = new ArrayList<>();
        l_r.add(left);
        l_r.add(right);
        return l_r;
    }

    private Node<T> join(Node<T> L, Node<T> R){
        if(L == null){
            root = R;
            return R;
        }
        root = splayUtil(L, findMax(L));
        root.setRight(R);
        return root;
    }

    private ArrayList<T> inOrder(Node<T> root, ArrayList<T> sorted){
        if(root == null){
            return sorted;
        }
        inOrder(root.getLeft(), sorted);
        sorted.add(root.getData());
        inOrder(root.getRight(), sorted);
        return sorted;
    }
    // Node class
    private class Node<T extends Comparable<T>> implements PrintableNode{

        private T data;
        private Node<T> left, right;

        public Node(T data){
            if(data == null){
                try{
                    throw new Exception("Null data is not valid here.");
                } catch(Exception e){
                    e.printStackTrace();
                }
            } else {
                this.data = data;
            }
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public T getData(){
            return data;
        }

        public void setData(T data){
            if (data == null) {
                try {
                    throw new Exception("Null data not allowed into tree");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else this.data = data;
        }

        @Override
        public Node<T> getLeft() {
            return left;
        }

        @Override
        public  Node<T> getRight() {
            return right;
        }

        @Override
        public String getText() {
            return data.toString();
        }
    }

    @Override
    public String toString() {

        System.out.println("Elements:" + inOrder(root, new ArrayList<>()));
        return (root != null) ? root.toString() : null;
    }

    public static void main(String[] args) {

        SplayTree<Integer> splayTree = new SplayTree<>();
        Scanner sc = new Scanner(System.in);
        int[] data = {2, 29, 26, -1, 10, 0, 2, 11};
        int c = 0;
        for (int i : data) {
            splayTree.insert(i);
        }

        while (c != 7) {
            System.out.println("1. Insert 2. Delete 3. Search 4.FindMin 5.FindMax 6. PrintTree 7. Exit");
            c = sc.nextInt();
            switch (c) {
                case 1:
                    System.out.println("Enter Data :");
                    splayTree.insert(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Enter Element to be Deleted:");
                    splayTree.delete(sc.nextInt());
                    break;
                case 3:
                    System.out.println("Enter Element to be Searched and Splayed:");
                    splayTree.search(sc.nextInt());
                    break;
                case 4:
                    System.out.println("Min: " + splayTree.findMin());
                    break;
                case 5:
                    System.out.println("Max: " + splayTree.findMax());
                    break;
                case 6:
                    System.out.println(TreePrinter.printTree(splayTree.getRoot()));
                    break;
                case 7:
                    sc.close();
                    break;
            }
        }
    }
}
