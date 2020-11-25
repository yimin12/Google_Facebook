package MultiThreading.ServiceWindow;

import org.w3c.dom.css.Counter;

import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/23 23:33
 *   @Description :
 *
 */
public class CounterWindowRunnable implements Runnable{

    // at most take 500 services
    private static final int MAX = 500;

    private int index = 1;

    private static final Object MUTEX = new Object();

    @Override
    public void run() {
        synchronized (MUTEX){
            while(index <= 500){
                try{
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.format("please the %d to the window %s, it is your turn\n", index++, Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        final CounterWindowRunnable task = new CounterWindowRunnable();
        new Thread(task, "1").start();
        new Thread(task, "2").start();
        new Thread(task, "3").start();
        new Thread(task, "4").start();
    }
}
