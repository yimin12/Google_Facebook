package DataStructure.Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/12 21:49
 *   @Description :
 *
 */
public class MyStack implements Stack<Integer>{

    // Use array with fixed size
    private int[] array;
    private int pos = 0;
    private int capacity;

    public MyStack(int maxSize){
        if(maxSize < 0){
            throw new IllegalArgumentException("The given capacity should greater than 0");
        }
        this.capacity = maxSize;
        this.array = new int[capacity];
    }

    public boolean isFull(){
        return pos > capacity;
    }

    @Override
    public int size() {
        return pos;
    }

    @Override
    public boolean isEmpty() {
        return pos == 0;
    }

    @Override
    public void push(Integer elem) {
        if(isFull()){
            throw new RuntimeException("Stack is full");
        }
        array[pos++] = elem;
    }

    @Override
    public Integer pop() {
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        return array[--pos];
    }

    @Override
    public Integer peek() {
        return array[pos - 1];
    }
}

interface Stack<T>{
    // return the number of elements in the stack
    public int size();

    // return if the stack is empty
    public boolean isEmpty();

    // push the element on the stack
    public void push(T elem);

    // pop the element off the stack
    public T pop();

    public T peek();
}
