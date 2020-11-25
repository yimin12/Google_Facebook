package MultiThreading.MyThreadPool.Interface;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/26 10:47
 *   @Description :
 *
 */

@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
