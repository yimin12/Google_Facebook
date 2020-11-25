package MultiThreading.TwoPhaseTerminationPattern;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 19:23
 *   @Description :
 *
 */
public class CounterIncrement extends Thread{

    private volatile boolean terminated  = false;
    private int counter = 0;

    @Override
    public void run() {
        try{
            while(!terminated){
                System.out.println(Thread.currentThread().getName() + " " + counter++);
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    private void clean(){
        System.out.println("Second phase completed" + counter);
    }

    public void close(){
        this.terminated = true;
        this.interrupt();
    }
}
