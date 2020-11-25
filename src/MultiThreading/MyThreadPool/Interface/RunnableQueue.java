package MultiThreading.MyThreadPool.Interface;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/26 10:45
 *   @Description :
 *
 */
public interface RunnableQueue {

    // offer the runnable mission to the queue
    void offer(Runnable runnable);

    // consume the runnable mission from the queue
    Runnable take();

    // get the size of runnable mission in queue
    int size();

}
