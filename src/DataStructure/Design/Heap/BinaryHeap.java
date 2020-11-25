package DataStructure.Design.Heap;

import java.util.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/13 16:18
 *   @Description :
 *      A min priority queue implementation using a binary heap. Use Hashmap and treeSet to optimize time complexity
 */
public class BinaryHeap <T extends Comparable<T>> {

    // A dynamic list to track the elements inside the heap
    private List<T> heap = null;

    // This map keeps track of the possible indices a particular node value is found in heap. Having this mapping lets us have O(log(n)) removals
    // and O(1) element containing verifying. Use space for optimizing time
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    // Construct and initially empty priority queue
    public BinaryHeap(){
        this(1);
    }

    public BinaryHeap(int size){
        heap = new ArrayList<>(size);
    }

    // Construct heap using heapify in O(n) time.
    // Input array type
    public BinaryHeap(T[] elems){
        int heapSize = elems.length;
        heap = new ArrayList<>(heapSize);
        // Put all element in heap
        for(int i = 0; i < elems.length; i++){
            mapAdd(elems[i], i);
            heap.add(elems[i]);
        }
        // heapify process, O(n)
        for(int i = Math.max(0, (heapSize/2) - 1); i >= 0; i--){
            percolateDown(i);
        }
    }

    // Input collections type
    public BinaryHeap(Collection<T> elems){
        this(elems.size());
        for(T ele:elems){
            add(ele);
        }
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return heap.size();
    }

    public void clear(){
        heap.clear();
        map.clear();
    }

    // Return the value of the element with the lowest priority in this heap. Return null if empty, O(1)
    public T peek(){
        if(isEmpty()) return null;
        return heap.get(0);
    }

    // Removes the root of heap, O(log(n))
    public T poll(){
        return removeAt(0);
    }

    // Test if an element is in heap, O(n) -> can be optimized to O(1) if using hash-heap
    public boolean contains(T elem){

        // Map lookup to check containment, O(1)
        if(elem == null) return false;
        return map.containsKey(elem);

        // Linear Scan
//        for(int i = 0; i < size(); i++){
//            if(heap.get(i).equals(elem)) return true;
//        }
//        return false;
    }

    // Adds an element to the heap, the element must not null and it uses O(log(n)) time
    public void add(T elem){
        if(elem == null) throw new IllegalArgumentException();
        heap.add(elem);
        int newIndex = size() - 1;
        mapAdd(elem, newIndex);
        percolateUp(newIndex);
    }

    // Tests if the value of node i <= node j. Assume i & j are valid indices, O(1)
    private boolean less(int i, int j){
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    // Perform bottom up node swap, O(log(n))
    private void percolateUp(int k){
        int parent = (k - 1) >> 1;
        while(k > 0 && less(k, parent)){
            // Exchange k with parent
            swap(parent, k);
            k = parent;
            // Keep percolating up
            parent = (k - 1) >> 1;
        }
    }

    // Top down node swap, O(log(n))
    private void percolateDown(int k){
        int heapSize = size();
        int left, right;
        while(true){
            left = 2 * k + 1;
            right = 2 * k + 2;
            // Find min from left, right, parent
            int smallest = left; // Assume
            if(right < heapSize && less(right, left)) smallest = right;
            if(left >= heapSize || less(k, smallest)) break; // Already to the right position
            swap(smallest, k);
            k = smallest;
        }
    }

    // Swap 2 nodes, assume i & j are valid
    private void swap(int i, int j){
        T ele_i = heap.get(i);
        T ele_j = heap.get(j);

        heap.set(i, ele_j);
        heap.set(j, ele_i);

        mapSwap(ele_i, ele_j, i, j);
    }

    // Removes specified element in the heap, O(n)
    public boolean remove(T element){
        if(element == null) return false;

        // Logarithmic removal with map, O(log(n))
        Integer index = mapGet(element);
        if(index != null) removeAt(index);
        return index != null;

        // Linear removal via search, O(n)
//        for(int i = 0; i < size(); i++){
//            if (element.equals(heap.get(i))){
//                removeAt(i);
//                return true;
//            }
//        }
//        return false;
    }

    // Removes a node at specified index, O(log(n))
    private T removeAt(int i){
        if(isEmpty()) return null;
        int indexOfLastElement = size() - 1;
        T removed_data = heap.get(i);
        swap(i, indexOfLastElement);

        // Obliterate the last value
        heap.remove(indexOfLastElement); // O(1)
        mapRemove(removed_data, indexOfLastElement);
        if(i == indexOfLastElement) return removed_data;

        T elem  = heap.get(i);
        percolateDown(i);

        // If percolate down did not work, the ith element will stay in the same place
        if(heap.get(i).equals(elem)) percolateUp(i);
        return removed_data;
    }

    // Recursively checks if this heap is a min heap. You can implement the max heap checking version
    public boolean isMinHeap(int k){
        int heapSize = size();
        if(k >= heapSize) return true;
        int left = 2 * k + 1;
        int right = 2 * k + 2;
        if(left < heapSize && less(k, left)) return false;
        if(right < heapSize && less(k ,right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }

    // Add a node value and its index to the map
    private void mapAdd(T value, int index){
        TreeSet<Integer> set = map.get(value);
        if(set == null){
            set = new TreeSet<>();
            set.add(index); // There are may be multiples same values, it will take O(log(n)) to sort in worst case
            map.put(value, set);
        } else {
            // Value is already in this map
            set.add(index);
        }
    }

    // Removes the index at the given value, O(log(n));
    private void mapRemove(T value, int index){
        TreeSet<Integer> set = map.get(value);
        set.remove(index); // take O(log(n)) time for removal
        if(set.size() == 0) map.remove(value);
    }

    // Extract an index position for the given value
    // NOTE: If a value exists multiple times in the heap, the highest index is returned. This is why we use treeSet here. O(1)
    private Integer mapGet(T value){
        TreeSet<Integer> set = map.get(value);
        if(set != null) return set.last();
        return null;
    }

    // Exchange the index of two nodes internally within the map
    private void mapSwap(T val1, T val2, int val1Index, int val2Index) {

        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        set1.remove(val1Index);
        set2.remove(val2Index);

        set1.add(val2Index);
        set2.add(val1Index);
    }

    @Override
    public String toString(){
        return heap.toString();
    }
}
