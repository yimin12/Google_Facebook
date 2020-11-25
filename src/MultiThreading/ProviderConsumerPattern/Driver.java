package MultiThreading.ProviderConsumerPattern;

import java.util.concurrent.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:34
 *   @Description :
 *
 */
public class Driver {

    public static void main(String[] args) {
        BlockingQueue<Data> queue = new LinkedBlockingDeque<>();

        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Producer p3 = new Producer(queue);

        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        ExecutorService cachePool = Executors.newCachedThreadPool();
        cachePool.execute(p1);
        cachePool.execute(p2);
        cachePool.execute(p3);
        cachePool.execute(c1);
        cachePool.execute(c2);
        cachePool.execute(c3);

        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        p1.stop();
        p2.stop();
        p3.stop();
    }
}
