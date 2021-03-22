package MultiThreading.DesignPattern.LatchPattern;

import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 18:24
 *   @Description :
 *
 */
public class Driver {

    public static void main(String[] args) {
        System.out.println("The first phase");
        int count = 5;

        MyCountDownLatch latch = new MyCountDownLatch(count);
        for(int i = 0; i < count; i++){
            new Thread(()->{
                try{
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(2);
                    latch.countDown();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }).start();
        }
        latch.await();

        System.out.println("First step is completed, and second phase is starting");
    }
}
