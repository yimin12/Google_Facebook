package DataStructure.Design.Tree;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/10 20:07
 *   @Description :
 *      Any comparable data is allowed
 *      within this tree (numbers, strings, comparable Objects, etc...). Supported operations include
 *      adding, removing, height, and containment checks. Furthermore, multiple tree traversal Iterators
 *      are provided including: 1) Preorder traversal 2) Inorder traversal 3) Postorder traversal 4)
 *      Level order traversal
 */
public class BinarySearchTree <T extends Comparable<T>>{

    // Record the number of nodes in BST
    private int nodeCount = 0;
    // BST is a rooted tree, and we maintain a handle on the root node
    private Node root = null;

    private class Node{
        T data;
        Node left, right;
        public Node(Node left, Node right, T data){
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }

    // Check if this binary tree is empty
    public boolean isEmpty(){
        return size() == 0;
    }
    // Get the number of node in this tree
    public int size(){
        return nodeCount;
    }
    // Add an element to this tree, return true if operation success
    public boolean add(T ele){
        // if the value is already exists in this binary tree, just ignore it
        if(contains(ele)){
            return false;
        } else {
            root = add(root, ele);
            nodeCount++;
            return true;
        }
    }
    // recursively add the value to binary tree, O(logn)
    private Node add(Node node, T ele){
        // Base case: leaf node
        if(node == null){
            node = new Node(null, null, ele);
            return node;
        }
        if(ele.compareTo(node.data) < 0){
            // if the current value is smaller, go left
            node.left = add(node.left, ele);
        } else {
            node.right = add(node.right, ele);
        }
        return node;
    }
    // Remove a value from this binary tree if exist, O(height) for search
    public boolean remove(T ele){
        if(contains(ele)){
            root = remove(root, ele);
            nodeCount--;
            return true;
        }
        return false;
    }
    private Node remove(Node node, T ele){
        if(node == null) return null;
        // Compare the ele with the current node
        int cmp = ele.compareTo(node.data);
        // dig into left sub-tree if cmp is less than 0
        if(cmp < 0){
            node.left = remove(node.left, ele);
        } else if(cmp > 0){
            node.right = remove(node.right, ele);
        } else {
            // Found the node we wish to remove
            if(node.left == null){
                // case 1:
                return node.right;
            } else if(node.right == null){
                // case 2:
                return node.left;
            } else {
                // Find the leftmost node the in thr right subtree
                Node tmp = findGreatestSmaller(node.right);
                // Swap the data
                node.data = tmp.data;
                node.right = remove(node.right, tmp.data); // tmp will be swapped and trapped by case 1 or case 2

                // or you can find the rightmost node in the left subtree
                // Node tmp1 = findSmallestLarger(node.left);
                // node.data = tmp1.data;
                // node.left = remove(node.left, tmp1.data);
            }
        }
        return node;
    }
    // find the leftmost node
    private Node findGreatestSmaller(Node node){
        while(node.left != null) node = node.left;
        return node;
    }
    // find the rightmost node
    private Node findSmallestLarger(Node node){
        while(node.right != null) node = node.right;
        return node;
    }
    // return true if element exists in the tree, O(height)
    public boolean contains(T ele){
        return contains(root, ele);
    }
    private boolean contains(Node node, T ele){
        // Base case
        if(node == null) return false;
        int cmp = ele.compareTo(node.data);
        if(cmp < 0){
            return contains(node.left, ele);
        } else if(cmp > 0) return contains(node.right, ele);
        else return true;
    }
    // Compute the height of tree, O(height)
    public int height(){
        return height(root);
    }
    private int height(Node node){
        if(node == null) return 0;
        return Math.max(height(root.left), height(root.left)) + 1;
    }

    // Traverse the binary search tree, all are O(n)
    public Iterator<T> traverse(TreeTraversalOrder order){
        switch (order){
            case PRE_ORDER:
                return preOrderTraversal();
            case POST_ORDER:
                return postOrderTraversal();
            case IN_ORDER:
                return inOrderTraversal();
            case LEVEL_ORDER:
                return preOrderTraversal();
            default:
                return null;
        }
    }
    // All the traverse will be under iterator mode
    private Iterator<T> preOrderTraversal(){
        final int expectedNodeCount = nodeCount;
        final Deque<Node> stack = new ArrayDeque<>(); // using the java.util rather than my stack
        stack.push(root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                // if the number does not match, it has concurrency problem
                if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node node = stack.pop();
                if(node.right != null) stack.push(node.right);
                if(node.left != null) stack.push(node.left);
                return node.data;
            }
        };
    }
    private Iterator<T> inOrderTraversal(){
        final int expectedNodeCount = nodeCount;
        final Deque<Node> stack = new ArrayDeque<>(); // using the java.util rather than my stack
        stack.push(root);
        return new Iterator<T>(){
            Node prev = root;
            @Override
            public boolean hasNext() {
                if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                while(prev != null && prev.left != null){
                    stack.push(prev.left);
                    prev = prev.left;
                }
                Node node = stack.pop();
                // Only move down right once
                if(node.right != null){
                    stack.push(node.right);
                    prev = node.right;
                }
                return node.data;
            }
        };
    }
    // you can also implement postOrder by using preOrder, and reverse the result at the end
    private Iterator<T> postOrderTraversal(){
        final int expectedNodeCount = nodeCount;
        final Deque<Node> stack1 = new ArrayDeque<>();
        final Deque<Node> stack2 = new ArrayDeque<>();
        stack1.push(root);
        while(!stack1.isEmpty()){
            Node node = stack1.pop();
            if(node != null){
                stack2.push(node);
                if(node.left != null) stack1.push(node.left);
                if(node.right != null) stack2.push(node.right);
            }
        }
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return stack2.pop().data;
            }
        };
    }
    // Use BFS to traverse it. You can implement it in zigzags order
    private Iterator<T> levelOrderTraversal(){
        final int expectedNodeCount = nodeCount;
        final Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if(expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                Node node = queue.poll();
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                return node.data;
            }
        };
    }
}
enum TreeTraversalOrder {
    PRE_ORDER, IN_ORDER, POST_ORDER, LEVEL_ORDER
}
