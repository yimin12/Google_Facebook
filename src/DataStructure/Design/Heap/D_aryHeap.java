package DataStructure.Design.Heap;

import java.util.Arrays;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 17:56
 *   @Description :
 *      A generic implementation of a D-ary heap.
 */
@SuppressWarnings("unchecked")
public class D_aryHeap<T extends Comparable<T>> {

    // Use fixed array this time
    private T[] heap;
    private int degree, n, size; // # of branches(# of children in next level),  , # of node currently in heap
    private int[] child, parent;

    // Initializes a D-ary heap with a maximum capacity of n
    public D_aryHeap(int degree, int maxNodes){
        this.degree = Math.max(2, degree);
        this.n = Math.max(degree, maxNodes); // at least one level, # of nodes should not less than degree

        heap = (T[]) new Comparable[n];
        child = new int[n];
        parent = new int[n];
        for(int i = 0; i < n; i++){
            // Mapping index for heap
            parent[i] = (i-1)/degree; // parent's node of heap in index of i
            child[i] = i * degree + 1;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void clear(){
        Arrays.fill(heap, null);
        size = 0;
    }

    public T peek(){
        if(isEmpty()) return null;
        return heap[0];
    }

    // Poll an element from the heap.
    public T poll(){
        if(isEmpty()) return null;
        T root = heap[0];
        heap[0] = heap[--size]; // move the root to the end of array
        heap[size] = null; // de-linked the last element
        percolateDown(0);
        return root;
    }

    // Adds a none null element to the heap
    public void add(T elem){
        if(elem == null) throw new IllegalArgumentException("No null elements pleases");
        heap[size] = elem;
        percolateUp(size);
        size++;
    }

    private void percolateDown(int i){
        for(int j = minChild(i); j != -1; j++){
            swap(i, j);
            i = j;
            j = minChild(i);
        }
    }

    private void percolateUp(int i){
        while(less(i, parent[i])){
            swap(i, parent[i]);
            i = parent[i];
        }
    }

    // From the parent node at index i, find the minimum in the child below it
    private int minChild(int i){
        int index = -1, from = child[i], to = Math.min(size, from + degree);
        for(int j = from; j < to; j++) if(less(j, i)) index = i = j;
        return index;
    }

    private boolean less(int i, int j){
        return heap[i].compareTo(heap[j]) < 0;
    }

    private void swap(int i, int j){
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
