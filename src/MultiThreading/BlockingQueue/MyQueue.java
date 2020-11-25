package MultiThreading.BlockingQueue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/24 0:28
 *   @Description :
 *
 */
public class MyQueue {

    private LinkedList<Object> list = new LinkedList<>();

    private AtomicInteger count = new AtomicInteger();

    private final int minSize = 0;
    private final int maxSize;

    public MyQueue(int maxSize){
        this.maxSize = maxSize;
    }

    private final Object LOCK = new Object();

    public void put(Object object){
        synchronized (LOCK){
            while(count.get() == this.maxSize){
                try{
                    LOCK.wait();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            list.add(object);
            count.incrementAndGet();
            LOCK.notifyAll();
            System.out.println("Adding new element: " + object.toString());
        }
    }

    public Object take(){
        Object ret = null;
        synchronized (LOCK){
            while(count.get() == this.minSize){
                try{
                    LOCK.wait();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            ret = list.removeFirst();
            count.decrementAndGet();
            LOCK.notifyAll();
        }
        return ret;
    }

    public int getSize(){
        return this.count.get();
    }

    public static void main(String[] args) {
        final MyQueue mq = new MyQueue(5);
        mq.put("a");
        mq.put("b");
        mq.put("c");
        mq.put("d");
        mq.put("e");

        System.out.println("Current size of myQueue : " + mq.getSize());
        Thread t1 = new Thread(()->{
           mq.put("f");
           mq.put("g");
        },"t1");

        Thread t2 = new Thread(()->{
            Object o1 =  mq.take();
            System.out.println("the removing element is : " + o1);
            Object o2 =  mq.take();
            System.out.println("the removing element is : " + o2);

        },"t2");
        t1.start();

        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        t2.start();
    }
}
