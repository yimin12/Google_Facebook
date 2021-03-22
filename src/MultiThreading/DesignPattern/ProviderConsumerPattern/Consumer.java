package MultiThreading.DesignPattern.ProviderConsumerPattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:32
 *   @Description :
 *
 */
public class Consumer implements Runnable {

    private BlockingQueue<Data> queue;

    public Consumer(BlockingQueue<Data> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try{
                Data data = this.queue.take();
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
                System.out.println("当前消费线程：" + Thread.currentThread().getName()
                        + "，消费成功，消费数据 id 为：" + data.getId());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
