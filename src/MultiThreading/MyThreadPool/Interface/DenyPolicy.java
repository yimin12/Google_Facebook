package MultiThreading.MyThreadPool.Interface;

import MultiThreading.MyThreadPool.Exception.RunnableDenyException;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/26 23:00
 *   @Description :
 *
 */
@FunctionalInterface
public interface DenyPolicy {
    
    void reject(Runnable runnable, YMThreadPool threadPool);

    // discard the mission directly
    class DiscardDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, YMThreadPool threadPool) {
            // do nothing
        }
    }

    // throw exception to the user when the mission reject
    class AbortDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, YMThreadPool threadPool) {
            throw new RunnableDenyException("Mission " + runnable + " rejected!");
        }
    }

    // run the mission in the submitted thread
    class RunnerDenyPolicy implements DenyPolicy{
        @Override
        public void reject(Runnable runnable, YMThreadPool threadPool) {
            if(!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}
