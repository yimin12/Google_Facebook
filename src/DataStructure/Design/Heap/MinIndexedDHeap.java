package DataStructure.Design.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 19:56
 *   @Description :
 *
 */
public class MinIndexedDHeap<T extends Comparable<T>> {

    // Current number of elements in the heap
    private int size;
    // Maximum number of elements in the heap
    private final int max;
    // Degree of every node in the heap
    private final int degree;
    // Look up arrays to track its child/parent indexes of each node
    private final int[] child, parent;
    // Position map (pm) maps Key Indexes (ki) to where the position of that key is represented in the heap in domain [0, size)
    public final int[] pm;
    // Inverse Map (im) stores the indexes of the keys in the range [0, size) which make up the heap. It should be noted that 'im' and 'pm' are inverses of each other
    // so: pm[im[i]] = im[pm[i]] = i;
    public final int[] im;

    // The values associated with the keys. This array is indexed by the key indexes (aka 'ki')
    public final Object[] values;

    // Init D-ary heap with a maximum capacity of maxSize
    public MinIndexedDHeap(int degree, int maxSize){
        if(maxSize <= 0) throw new IllegalArgumentException("maxSize should greater than 0");
        this.degree = Math.max(2, degree); // should not less than binary
        this.max = Math.max(degree + 1, maxSize);

        // init these containers
        im = new int[max];
        pm = new int[max];
        child = new int[max];
        parent = new int[max];
        values = new Object[max];

        for(int i = 0; i < max; i++){
            parent[i] = (i - 1) / degree;
            child[i] = i * degree + 1;
            pm[i] = im[i] = -1; // init as -1
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean contains(int ki){
        keyInBound(ki);
        return pm[ki] != -1;
    }

    public int peekMinKeyIndex(){
        isNotEmpty();
        return im[0];
    }

    public int pollMinKeyIndex(){
        isNotEmpty();
        int minki = peekMinKeyIndex();
        delete(minki);
        return minki;
    }

    public T pollMinValue(){
        isNotEmpty();
        T minValue = peekMinValue();
        delete(peekMinKeyIndex());
        return minValue;
    }

    public T peekMinValue(){
        isNotEmpty();
        return (T) values[im[0]];
    }

    public void insert(int ki, T value){
        if(contains(ki)) throw new IllegalArgumentException(" index already exists: recived + " + ki);
        valueNotNull(value);
        pm[ki] = size; // key -> index
        im[size] = ki;  // index -> key
        values[ki] = value; // key -> value
        percolateUp(size++);
    }

    public T valueOf(int ki){
        keyExists(ki);
        return (T) values[ki];
    }

    public T delete(int ki){
        keyExists(ki);
        final int i = pm[ki];
        swap(i, --size);
        percolateUp(i);
        percolateDown(i);
        T value = (T) values[ki];
        values[ki] = null;
        pm[ki] = -1;
        im[size] = -1;
        return value;
    }

    public T update(int ki, T value){
        keyExistsAndValueNotNull(ki, value);
        final int i = pm[ki];
        T oldValue = (T) values[ki];
        values[ki] =  value;
        percolateDown(i);
        percolateUp(i);
        return oldValue;
    }

   // Strictly decreases the value associated with 'ki' to 'value'
    public void decrease(int ki, T value){
        keyExistsAndValueNotNull(ki, value);
        if(less(values[ki], value)){
            values[ki] = value;
            percolateUp(pm[ki]);
        }
    }

    // Strictly increases the value associated with 'ki' to 'value'
    public void increase(int ki, T value){
        keyExistsAndValueNotNull(ki, value);
        if(less(values[ki], value)){
            values[ki] = value;
            percolateDown(pm[ki]);
        }
    }


    /**
     * Basic operation for d-ary tree
     */
    private void percolateDown(int i){
        for(int j = minChild(i); j != -1;){
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
    private int minChild(int i){
        int index = -1, from = child[i], to = Math.min(size, from + degree);
        for(int j = from; j < to; j++) if(less(j, i)) index = i = j;
        return index;
    }

    private void swap(int i, int j){
        pm[im[j]] = i;
        pm[im[i]] = j;
        int tmp = im[i];
        im[i] = im[j];
        im[j] = tmp;
    }
    /**
     * Two type of comparable function
     * @param i
     * @param j
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean less(int i, int j){
        return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
    }

    @SuppressWarnings("unchecked")
    private boolean less(Object obj1, Object obj2){
        return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
    }

    @Override
    public String toString() {
        List<Integer> lst = new ArrayList<>(size);
        for (int i = 0; i < size; i++) lst.add(im[i]);
        return lst.toString();
    }

    /**
     * Helper function for throwing exception
     */
    private void isNotEmpty(){
        if(isEmpty()) throw new NoSuchElementException("Priority queue is empty");
    }

    private void keyExistsAndValueNotNull(int ki, Object value){
        keyExists(ki);
        valueNotNull(value);
    }

    private void keyExists(int ki){
        if(!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
    }

    private void valueNotNull(Object value){
        if(value == null) throw new IllegalArgumentException(" Value can not be null");
    }

    private void keyInBound(int ki){
        if(ki < 0 || ki >= this.max){
            throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
        }
    }

    /**
     * Test function
     */
    public boolean isMinHeap(){
        return isMinHeap(0);
    }

    // Check it recursively, O(n)
    private boolean isMinHeap(int i){
        int from = child[i], to = Math.min(this.max, i + degree);
        for(int j = from; j < to; j++){
            if(!less(i, j)) return false;
            if(!isMinHeap(j)) return false;
        }
        return true;
    }
}
