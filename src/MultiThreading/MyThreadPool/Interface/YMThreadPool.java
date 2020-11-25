package MultiThreading.MyThreadPool.Interface;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/26 10:40
 *   @Description :
 *      this will involve basic function of thread pool
 */
public interface YMThreadPool {

    // submit the mission to the thread pool
    void execute(Runnable runnable);

    // shutdown the thread pool
    void shutdown();

    // get the init-size of the thread pool
    int getInitSize();

    // get the max-size of the thread pool
    int getMaxSize();

    // get the core-size of the thread pool
    int getCoreSize();

    // get the number mission in queue
    int getQueueSize();

    // get the number of active thread
    int getActiveCount();

    // whether it has been shutdown
    boolean isShutdown();
}
