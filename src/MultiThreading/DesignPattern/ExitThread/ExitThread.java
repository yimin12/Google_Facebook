package MultiThreading.DesignPattern.ExitThread;

import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/23 22:23
 *   @Description :
 *      How to stop a running thread?
 *      1. JDK has a deprecated method called stop(), we will not use this method in the future because it might not release the locker of the monitor when you stop a thread.
 *      2. Catch the interruption exception and then stop the thread
 *      3. Use volatile key word
 */
public class ExitThread {

    // Use exception to shutdown a thread
    public static void stop_1(String... args) throws InterruptedException{
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("I will start a work");
                while(!isInterrupted()){

                }
                System.out.println("I will be exiting");
            }
        };
        t.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("System will be shutdown");
        t.interrupt();
    }

    // Use volatile keyword to shutdown a thread
    public static void stop_2(String... args) throws InterruptedException {
        MyTask t = new MyTask();
        t.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("System will be shutdown!");
        t.close();
    }


    public static void main(String[] args) throws InterruptedException {
//        stop_1();
        stop_2();
    }
}

class MyTask extends Thread{

    private volatile boolean flag = false;

    @Override
    public void run() {
        System.out.println("I will start work");
        while(!flag){
            // working
        }
        System.out.println("I will be exiting");
    }

    public void close(){
        this.flag = true;
    }
}
