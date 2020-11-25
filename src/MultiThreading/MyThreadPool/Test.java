package MultiThreading.MyThreadPool;

import MultiThreading.MyThreadPool.Interface.YMThreadPool;

import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 15:29
 *   @Description :
 *
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        final YMThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
        for(int i = 0; i < 20; i++){
            threadPool.execute(()->{
                try{
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "is running now");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            });
        }
        while(true){
            System.out.println("threadPool.getActiveCount() = " + threadPool.getActiveCount());
            System.out.println("threadPool.getQueueSize() = " + threadPool.getQueueSize());
            System.out.println("threadPool.getCoreSize() = " + threadPool.getCoreSize());
            System.out.println("threadPool.getMaxSize() = " + threadPool.getMaxSize());
            System.out.println("-----------------------------------------------------------");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
