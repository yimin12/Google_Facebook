package DataStructure.Design;
import java.util.Iterator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/10 18:47
 *   @Description :
 *      Double linked list
 */
public class DoublyLinkedList<T> implements Iterable<T>{

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // self define Node for double linked list
    private static class Node<T>{
        private T data;
        private Node<T> prev, next;
        public Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Empty the linked list, O(n)
    public void clear(){
        Node<T> censor = head;
        while(censor != null){
            Node<T> next = censor.next;
            censor.prev = censor.next = null;
            censor.data = null;
            censor = next;
        }
    }

    // return the size of linked list
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    // Add an element to the tail of linked list, O(1)
    public void add(T element){
        addLast(element);
    }
    private void addLast(T element){
        if(isEmpty()){
            head = tail = new Node<T>(element, null, null);
        } else {
            tail.next = new Node<T>(element, tail, null);
            tail = tail.next;
        }
        size++;
    }
    // Add an element to the head of the linked list, O(1)
    public void addFirst(T element){
        if(isEmpty()){
            head = tail = new Node<T>(element, null, null);
        } else{
            head.prev = new Node<T>(element, null, head);
            head = head.prev;
        }
        size++;
    }
    // Add element at any index, O(n) in worst case
    public void addAt(int index, T data) throws Exception{
        if(index < 0){
            throw new Exception("The given index is invalided");
        }
        if(index == 0){
            addFirst(data);
            return;
        } else if(index == size){
            addLast(data);
            return;
        }
        Node<T> temp = head ;
        for(int i = 0; i < index - 1; i++){
            temp = temp.next;
        }
        Node<T> newNode = new Node<T>(data, temp, temp.next);
        temp.next.prev = newNode;
        temp.next = newNode;
        size++;
    }

    // Check the value of the first node if it exists, O(1)
    public T peekFirst(){
        if(isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    // Check the value of the last node if it exists, O(1)
    public T peekLast(){
        if(isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    // Remove the first value at the head of the linked list, O(1)
    public T removeFirst(){
        if(isEmpty()) throw new RuntimeException("Empty List");
        T data  = head.data;
        head = head.next;
        size--;
        if(isEmpty()){
            tail = null;
        } else {
            head = null;
        }
        return data;
    }

    public T removeLast(){
        if(isEmpty()) throw new RuntimeException("Empty List");
        T data = tail.data;
        tail = tail.prev;
        size--;
        if(isEmpty()){
            head = null;
        } else tail.next = null;
        return data;
    }

    // Remove an arbitrary node from the linked list, O(1)
    private T remove(Node<T> node){
        // if remove the head or tail
        if(node.prev == null) return removeFirst();
        if(node.next == null) return removeLast();
        // remove the middle point
        node.next.prev = node.prev;
        node.prev.next = node.next;
        // Temporarily store the data
        T data = node.data;
        // Clean memory
        node.data = null;
        node.prev = node.next = null;
        size--;
        return data;
    }

    // Remove a node at a particular index, O(n)
    public T removeAt(int index){
        // sanity check
        if(index < 0 || index >= size) throw new IllegalArgumentException();
        int i;
        Node<T> censor;
        // Small trick to Optimize Searching time, find corresponding node, O(n/2)
        if(index < size/2){
            for(i = 0, censor = head; i != index; i++){
                censor = censor.next;
            }
        } else {
            for(i = size - 1, censor = tail; i != index; i--){
                censor = censor.prev;
            }
        }
        return remove(censor);
    }

    // Remove particular value in linked-list, O(n)
    public boolean remove(Object obj){
        Node<T> censor = head;
        // support searching for null
        if(obj == null){
            for(censor = head; censor != null; censor= censor.next){
                if(censor.data == null){
                    remove(censor);
                    return true;
                }
            }
        } else {
            // search for non null object
            for(censor = head; censor != null; censor = censor.next){
                if(obj.equals(censor.data)){
                    remove(censor);
                    return true;
                }
            }
        }
        return false;
    }

    // Find the index of a particular value in the linked list, O(n)
    public int indexOf(Object obj){
        int index = 0;
        Node<T> censor = head;
        // Support searching the null
        if(obj == null){
            for(;censor != null; censor = censor.next, index++){
                if(censor.data == null){
                    return index;
                }
            }
        } else {
            for(; censor != null; censor = censor.next, index++){
                if (obj.equals(censor.data)){
                    return index;
                }
            }
        }
        return -1;
    }

    // Check is a value is contained within the linked list, O(n)
    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> censor = head;
            DoublyLinkedList list = new DoublyLinkedList();
            @Override
            public boolean hasNext() {
                return censor != null;
            }

            @Override
            public T next() {
                T data = censor.data;
                censor = censor.next;
                return data;
            }

            @Override
            public void remove() {
                // remove the previous one
                Node<T> temp = censor.prev;
                list.remove(temp);
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> censor = head;
        while(censor != null){
            sb.append(censor.data);
            if(censor.next != null){
                sb.append(", ");
            }
            censor = censor.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}

