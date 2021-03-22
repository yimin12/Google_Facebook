package MultiThreading.AtomicOperations;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/19 23:11
 *   @Description :
 *
 */
public class AIFUTest {

    private volatile int i;

    private AtomicInteger j = new AtomicInteger();

    // update the atomicInteger
    private AtomicIntegerFieldUpdater<AIFUTest> updater = AtomicIntegerFieldUpdater.newUpdater(AIFUTest.class, "i");

    public void update(int newValue){
        updater.compareAndSet(this, i, newValue);
    }

    public int get(){
        return i;
    }

    public static void main(String[] args) {
        AIFUTest test = new AIFUTest();
        test.update(10);
        System.out.println(test.get());
    }
}
