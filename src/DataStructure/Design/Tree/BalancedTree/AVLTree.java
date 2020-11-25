package DataStructure.Design.Tree.BalancedTree;

import DataStructure.Design.Utils.TreePrinter;
import DataStructure.Design.Utils.TreePrinter.PrintableNode;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/11 19:44
 *   @Description :
 *      参考玩转数据结构
 */
public class AVLTree<T extends Comparable<T>> implements Iterable {

    public Node root; // can use singleton here
    private int nodeCount = 0; // Tracks the number of nodes inside the tree

    // get the height of root
    public int height(){
        if(root == null) return 0;
        return root.height;
    }

    // Get the number of node
    public int size(){
        return nodeCount;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    // Return true/false depending on whether a value exists in the tree
    public boolean contains(T value){
        return contains(root, value);
    }
    private boolean contains(Node node, T value){
        if(node == null){
            return false;
        }
        int cmp = value.compareTo(node.value);
        if(cmp < 0) return contains(node.left, value);
        if(cmp > 0) return contains(node.right, value);
        return true;
    }

    public boolean insert(T value){
        if(value == null) return false;
        if(!contains(root, value)){
            root = insert(root, value);
            nodeCount++;
            return true;
        }
        return false;
    }
    // Inserts a value inside in the AVL tree
    public Node insert(Node node, T value){
        // base case
        if(node == null){
            return new Node(value);
        }
        int cmp = value.compareTo(node.value);
        if(cmp < 0){
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        // cmp != 0, check before
        update(node);
        return balance(node);
    }

    // Update the node's height and balance factor
    private void update(Node node){
        int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
        int rightNodeHeight = (node.right == null) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.bf = rightNodeHeight - leftNodeHeight;
    }

    // Re-balance a node if its balance factor is +2 or -2
    private Node balance(Node node){
        // Left heavy subtree
        if(node.bf == -2){
            // Left-Left case
            if(node.left.bf <= 0){
                return leftLeft(node);
            } else {
                 // Left-Right
                return leftRight(node);
            }
        } else if(node.bf == 2){
            if(node.right.bf >= 0){
                return rightRight(node);
            } else {
                return rightLeft(node);
            }
        }
        return node;
    }

    private Node leftLeft(Node node){
        return rightRotation(node);
    }

    private Node leftRight(Node node){
        node.left = leftRotation(node.left);
        return leftLeft(node);
    }

    private Node rightRight(Node node){
        return leftRotation(node);
    }

    private Node rightLeft(Node node){
        node.right = rightRotation(node.right);
        return rightRight(node);
    }

    private Node leftRotation(Node node){
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        // update their height and bf of
        update(node);
        update(newParent);
        return newParent;
    }

    private Node rightRotation(Node node){
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        update(node);
        update(newParent);
        return newParent;
    }

    // Remove a value from this bst if exists, O(logn)
    public boolean remove(T ele){
        if(ele == null) return false;
        if(contains(root, ele)){
            root = remove(root, ele);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T value){
        if(node == null){
            return null;
        }
        int cmp = value.compareTo(node.value);
        if(cmp < 0){
            node.left = remove(node.left, value);
        } else if(cmp > 0){
            node.right = remove(node.right, value);
        } else {
            if(node.left == null){
                return node.right;
            } else if(root.right == null){
                return node.left;
            } else{
                if(node.left.height > node.right.height){
                    T successor = findMax(node.left);
                    node.value = successor;
                    node.left = remove(node.left, successor);
                } else {
                    T successor = findMin(node.right);
                    node.value = successor;
                    node.right = remove(node.right, successor);
                }
            }
        }
        update(node);
        return balance(node);
    }

    private T findMin(Node node){
        while(node.left != null){
            node = node.left;
        }
        return node.value;
    }

    private T findMax(Node node){
        while(node.right != null){
            node = node.right;
        }
        return node.value;
    }

    // Implement it inOrder traverse
    @Override
    public Iterator<T> iterator() {

        final int expectedNodeCount = nodeCount;
        final Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        return new Iterator() {
            Node censor = root;
            @Override
            public boolean hasNext() {
                if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                while(censor != null && censor.left != null){
                    stack.push(censor.left);
                    censor = censor.left;
                }
                Node node = stack.pop();
                if(node.right != null){
                    stack.push(node.right);
                    censor = censor.right;
                }
                return node.value;
            }
        };
    }

    @Override
    public String toString(){
        return TreePrinter.printTree(root);
    }

    // Validate whether it is Binary Search Tree
    public boolean validateBST(Node node){
        if(node == null) return true;
        T value = node.value;
        boolean isValid = true;
        if(node.left != null) isValid = isValid && node.left.value.compareTo(value) < 0;
        if(node.right != null) isValid = isValid && node.right.value.compareTo(value) > 0;
        return isValid && validateBST(node.left) && validateBST(node.right);
    }

    // internal class
    public class Node implements PrintableNode {

        // Short for balance factor
        public int bf;
        // Value contained within the node
        public T value;
        // The height of current node
        public int height;
        // Two child's pointers
        public Node left, right;

        public Node(T value){
            this.value = value;
        }

        @Override
        public PrintableNode getLeft() {
            return left;
        }

        @Override
        public PrintableNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return value.toString();
        }
    }
}
