package MultiThreading.DesignPattern.GuardedSuspension;

import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 18:16
 *   @Description :
 *
 */
public class Driver {

    public static void main(String[] args) {
        final GuardedSuspensionQueue queue = new GuardedSuspensionQueue(100);
        new Thread(()->{
            for(int i = 1; i <= 100; i++){
                try{
                    queue.offer(i);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try{
                TimeUnit.SECONDS.sleep(2);
                while(true){
                    System.out.println("取出成功，取出的元素为：" + queue.take());
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }).start();
    }
}
