package MultiThreading.DesignPattern.ContextPattern;

import java.util.stream.IntStream;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 17:04
 *   @Description :
 *
 */
public class Driver {

    public static void main(String[] args) {
        IntStream.range(0,5).forEach(i -> new Thread(new ExecutionTask()).start());
    }
}
