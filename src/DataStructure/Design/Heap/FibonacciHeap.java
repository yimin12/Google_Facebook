package DataStructure.Design.Heap;

import com.sun.istack.internal.Nullable;

import java.util.*;

import static java.lang.Math.log;
import static java.lang.Math.sqrt;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 21:46
 *   @Description :
 *
 */
public class FibonacciHeap<E> implements Queue<E> {

    private static final double LOG_PHI = log((1 + sqrt(5)) / 2);
    private final Set<E> elementIndex = new HashSet<E>();
    private final Comparator<? super E> comparator;
    private int size = 0;
    private int trees = 0;
    private int markedNodes = 0;
    private Node<E> minimumNode;

    // Constructor
    public FibonacciHeap(){
        this(null); //
    }

    public FibonacciHeap(/*@Nullable*/ Comparator<? super E> comparator){
        this.comparator = comparator;
    }

    private void moveToRoot(Node<E> node){

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }



    private static class Node<E>{
        private final E element;
        // Node points to itself
        private Node<E> parent;
        private Node<E> left = this;
        private Node<E> right = this;
        private Node<E> child;
        private int degree; // children in next level in current tree
        private boolean marked; // pruning the branches need to mark its parent

        public Node(E element){
            this.element = element;
            degree = 0;
            setParent(null);
            setChild(null);
            setLeft(this);
            setRight(this);
            setMarked(false);
        }

        public E getElement() {
            return element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public Node<E> getChild() {
            return child;
        }

        public void setChild(Node<E> child) {
            this.child = child;
        }

        public int getDegree() {
            return degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public boolean isMarked() {
            return marked;
        }

        public void setMarked(boolean marked) {
            this.marked = marked;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }
}
