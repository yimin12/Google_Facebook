package DataStructure.Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/12 21:32
 *   @Description :
 *
 */
public class MyQueue implements Queue<Integer>{

    private int[] data; // Implement it with fixed size array, circular array
    private int front, end; // Two pointer to maintain the principle of queue
    private int size, capacity;

    // maxSize is the maximum number of items that can be in the queue at any given time
    public MyQueue(int maxSize){
        front = end = size = 0;
        this.capacity = maxSize;
        this.data = new int[capacity];
    }

    public boolean isFull(){
        return size == capacity;
    }

    @Override
    public void offer(Integer elem) {
        if(isFull()){
            throw new RuntimeException("Queue is full");
        }
        // offer -> mod after
        data[end++] = elem;
        size++;
        end = end % capacity;
    }

    /**
     * 拿东西，先取模再拿（有->无）
     * 放东西，先放再取模（无->有）
     * @return
     */
    // poll or peek, mod first
    @Override
    public Integer poll() {
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        size--;
        front = front % capacity;
        return data[front++];
    }

    @Override
    public Integer peek() {
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        front = front % data.length;
        return data[front];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

// Basic Interface with generic
interface Queue<T>{

    public void offer(T elem);

    public T poll();

    public T peek();

    public int size();

    public boolean isEmpty();

}