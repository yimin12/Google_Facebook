package MultiThreading.DesignPattern.ProviderConsumerPattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:26
 *   @Description :
 *
 */
public class Producer implements Runnable{

    // shared resource
    private BlockingQueue<Data> queue;

    public Producer(BlockingQueue<Data> queue){
        this.queue = queue;
    }

    private volatile boolean isRunning  = true;

    private static AtomicInteger count = new AtomicInteger();

    @Override
    public void run() {
        while(isRunning){
            try{
                // return an random number in current thread
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
                int id = count.incrementAndGet();
                Data data = new Data(id, "data is " + id);
                System.out.println("当前生成线程：" + Thread.currentThread().getName()
                        + "，获取了数据，数据 id：" + id + "，进行装载到公共缓存区中...");
                if(!this.queue.offer(data, 2, TimeUnit.SECONDS)){
                    System.out.println("Submitting the mission fail"); // it will try twice and you can do something else here
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void stop(){
        this.isRunning = false;
    }
}
