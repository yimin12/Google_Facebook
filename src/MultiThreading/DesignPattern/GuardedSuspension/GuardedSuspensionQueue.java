package MultiThreading.DesignPattern.GuardedSuspension;

import java.util.LinkedList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 18:07
 *   @Description :
 *      Suspension 是“挂起”、“暂停”的意思，而Guarded 则是“担保”的意思，连在一起
        就是确保挂起。当线程在访问某个对象时，发现条件不满足，就暂时挂起等待条件满足时
        再次访问。
        Guarded Suspension 设计模式是很多设计模式的基础，比如生产者消费者模式，同
        样在Java 并发包中的BlockingQueue 中也大量使用到了Guarded Suspension 设计模
        式
 */
public class GuardedSuspensionQueue {

    private final LinkedList<Integer> QUEUE = new LinkedList<>();

    private final int LIMIT;

    public GuardedSuspensionQueue(int limit){
        LIMIT = limit;
    }

    public void offer(Integer data) throws InterruptedException{
        synchronized (this){
            while(QUEUE.size() >= LIMIT){
                System.out.println("The queue is full");
                this.wait();
            }
            QUEUE.addLast(data);
            System.out.println("Successfully inserted : " +data);
            this.notifyAll();
        }
    }

    public Integer take() throws InterruptedException{
        synchronized (this){
            while(QUEUE.isEmpty()){
                System.out.println("----- The queue is empty -------");
                this.wait();
            }
            this.notifyAll();
            return QUEUE.removeFirst();
        }
    }
}
