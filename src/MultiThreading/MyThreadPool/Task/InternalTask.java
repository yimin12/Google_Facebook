package MultiThreading.MyThreadPool.Task;

import MultiThreading.MyThreadPool.Interface.RunnableQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/26 23:43
 *   @Description :
 *      InternalTask 是Runnable 的一个实现，主要用于线程池内部，该类会使用到
        RunnableQueue，然后不断地从queue 中取出某个Runnable，并运行Runnable 的run
        方法，除此之外，代码还对该类增加了一个开关方法stop，主要用于停止当前线程，一般
        在线程池销毁和线程数量维护的时候会使用到
 */
public class InternalTask implements Runnable{

    private final RunnableQueue  runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue){
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while(running && !Thread.currentThread().isInterrupted()){
            Runnable task = runnableQueue.take();
            task.run();
        }
    }

    public void stop(){
        this.running = false;
    }
}
