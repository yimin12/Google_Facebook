package MultiThreading.MyThreadPool.Task;

import MultiThreading.MyThreadPool.Interface.DenyPolicy;
import MultiThreading.MyThreadPool.Interface.RunnableQueue;
import MultiThreading.MyThreadPool.Interface.YMThreadPool;

import java.util.LinkedList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 11:43
 *   @Description :
 *
 */
public class LinkedRunnableQueue implements RunnableQueue {

    // the max number of threads we can use
    private final int limit;

    // the denyPolicy we use
    private final DenyPolicy denyPolicy;

    // container for the runnable mission
    private final LinkedList<Runnable> runnableList = new LinkedList<>();
    private final YMThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, YMThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList){
            if(runnableList.size() > limit){
                denyPolicy.reject(runnable, threadPool);
            } else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() {
        // case 1: currently we do not have mission, wait
        synchronized (runnableList){
            while(runnableList.isEmpty()){
                try{
                    runnableList.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            // case 2: take one mission
            return runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
       synchronized (runnableList){
           return runnableList.size();
       }
    }
}
