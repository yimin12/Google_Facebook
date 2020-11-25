package MultiThreading.MyThreadPool;

import MultiThreading.MyThreadPool.Interface.DenyPolicy;
import MultiThreading.MyThreadPool.Interface.RunnableQueue;
import MultiThreading.MyThreadPool.Interface.ThreadFactory;
import MultiThreading.MyThreadPool.Interface.YMThreadPool;
import MultiThreading.MyThreadPool.Task.InternalTask;
import MultiThreading.MyThreadPool.Task.LinkedRunnableQueue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 11:56
 *   @Description :
 *
 */
public class BasicThreadPool extends Thread implements YMThreadPool {

    private final int initSize;
    private final int maxSize;
    private final int coreSize;
    private int activeCount;

    private final RunnableQueue runnableQueue;
    private volatile boolean isShutdown = false;

    private final long keepAliveTime;
    private final TimeUnit timeUnit;

    // create the factory
    private final ThreadFactory threadFactory;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize, DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit, ThreadFactory threadFactory) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.threadFactory = threadFactory;
        init();
    }

    @Override
    public void run() {

        while(!isShutdown && !isInterrupted()){
            try{
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            synchronized (this){
                if(isShutdown){
                    break;
                }
                // 当前的队列中有任务尚未处理，并且 activeCount < coreSize 则继续扩容
                if(runnableQueue.size() > 0 && activeCount < coreSize){
                    for(int i = initSize; i < coreSize; i++){
                        newThread();
                    }
                    continue;
                }
                // 当前的队列中有任务尚未处理，并且 activeCount < maxSize 则继续扩容
                if(runnableQueue.size() > 0 && activeCount < maxSize){
                    for(int i = coreSize; i < maxSize; i++){
                        newThread();
                    }
                }
                // 当前的队列中没有任务，则需要回收，回收至 coreSize 即可
                if(runnableQueue.size() == 0 && activeCount > coreSize){
                    for(int i = coreSize; i < activeCount; i++){
                        removeThread();
                    }
                }
            }
        }
    }

    private void init(){
        this.start();
        for(int i = 0; i < initSize; i++){
            newThread();
        }
    }

    private void newThread(){
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread(){
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void execute(Runnable runnable) {
        if(this.isShutdown){
            throw new IllegalStateException("the thread pool is already destroyed");
        }
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this){
            if(isShutdown){
                return;
            }
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if(this.isShutdown){
            throw new IllegalStateException("This thread pool is already shutdown");
        }
        return initSize;
    }

    @Override
    public int getMaxSize() {
        if(this.isShutdown){
            throw new IllegalStateException("This thread pool is already shutdown");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if(this.isShutdown){
            throw new IllegalStateException("This thread pool is already shutdown");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if(this.isShutdown){
            throw new IllegalStateException("This thread pool is already shutdown");
        }
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        if(this.isShutdown){
            throw new IllegalStateException("This thread pool is already shutdown");
        }
        return this.activeCount;
    }

    @Override
    public boolean isShutdown() {
        if(this.isShutdown){
            throw new IllegalStateException("This thread pool is already shutdown");
        }
        return this.isShutdown;
    }

    // static inner factory
    private static class DefaultThreadFactory implements ThreadFactory{
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(0);
        private static final ThreadGroup GROUP = new ThreadGroup("MyThreadGroupPool-" + GROUP_COUNTER.getAndIncrement());
        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(GROUP, runnable, "thread-pool-" + COUNTER.getAndIncrement());
        }
    }

    // working Queue
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    // default strategy
    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    // constructor
    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize){
        this(initSize, maxSize, coreSize, queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS, DEFAULT_THREAD_FACTORY);
    }
}
