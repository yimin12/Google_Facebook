package Contest.Bloomberg;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/4/3 19:59
 *   @Description :
 *
 */
public class DesignBoundedBlockingQueue {

    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    private int[] q;
    private int tail = 0, head = 0, size = 0;

    public DesignBoundedBlockingQueue(int capacity) {
        q = new int[capacity];
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try{
            while(size == q.length){
                full.await();
            }
            q[tail ++] = element;
            tail %= q.length;
            size ++;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while(size == 0){
                empty.await();
            }
            int res = q[head ++];
            head %= q.length;
            size --;
            full.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try{
            return this.size;
        } finally {
            lock.unlock();
        }
    }
}
