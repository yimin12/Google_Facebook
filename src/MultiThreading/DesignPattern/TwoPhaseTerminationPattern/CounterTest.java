package MultiThreading.DesignPattern.TwoPhaseTerminationPattern;

import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 19:35
 *   @Description :
 *
 */
public class CounterTest {

    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();
        TimeUnit.SECONDS.sleep(5);
        counterIncrement.close();
    }
}
