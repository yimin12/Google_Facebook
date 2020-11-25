package MultiThreading.FlightSearch;

import java.util.List;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/23 21:48
 *   @Description :
 *      Image to implement a app that we can help our client to search the flight from A to B. It must guarantee multiple client can use this app at the same time
 */
public interface FlightQuery {

    /*
    以上代码中，FlightQuery 提供了一个返回方法，学到这里大家应该注意到了，不管
    是Thread 的run 方法，还是Runnable 接口，都是void 返回类型，如果你想通过某个线
    程的运行得到结果，就需要自己定义一个返回接口。
     */
    List<String> get();
}
