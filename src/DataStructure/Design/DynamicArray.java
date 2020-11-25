package DataStructure.Design;

import java.util.Iterator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/10 13:08
 *   @Description :
 *      Dynamic array, not static
 */
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T>{

    // container and its field
    private T[] array;
    private int len = 0; // length that user has set default
    private int capacity = 0; // actual array size

    public DynamicArray(){
        // default size if 16
        this(16);
    }

    public DynamicArray(int capacity){
        if(capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size(){
        return len;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public T get(int index){
        return array[index];
    }
    public void set(int index, T element){
        array[index] = element;
    }
    public void clear(){
        for(int i = 0; i < capacity; i++){
            array[i] = null;
        }
        len = 0;
    }
    // update it with O(n) time, but amortized O(1)
    public void add(T element){
        if(len + 1 >= capacity){
            if(capacity == 0) capacity = 1; // init
            else capacity += capacity >> 1;
            T[] new_arr = (T[]) new Object[capacity];
            for(int i = 0; i < len; i++){
                new_arr[i] = array[i];
            }
            array = new_arr;
        }
        array[len++] = element;
    }
    // Removes the element at the specified index in this list, O(n)
    public T removeAt(int rm_index){
        if(rm_index >= len || rm_index < 0) throw new IndexOutOfBoundsException();
        T data = array[rm_index];
        T[] new_arr = (T[]) new Object[len-1];
        for(int i = 0, j = 0; i < len; i++, j++){
            if(i == rm_index) j--;
            else new_arr[j] = array[i];
        }
        array = new_arr;
        capacity = --len;
        return data;
    }
    public boolean remove(Object obj){
        for(int i = 0; i < len; i++){
            if(array[i].equals(obj)){
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj){
        for(int i = 0; i < len; i++){
            if(array[i].equals(obj)){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }
    // StringBuilder support streaming style
    @Override
    public String toString() {
        if(len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for(int i = 0; i < len - 1; i++){
                sb.append(array[i] + ", ");
            }
            return sb.append(array[len-1] + "]").toString();
        }
    }
}
