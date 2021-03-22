package MultiThreading.AtomicOperations;

import java.util.concurrent.locks.LockSupport;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/17 1:14
 *   @Description :
 *
 */
public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("---------m1-------------");

        Thread t = new Thread("t1"){
            @Override
            public void run() {
                System.out.println("-------------t1---------------");
                LockSupport.park();
                System.out.println("-------------t2---------------");
            }
        };
        t.start();
        Thread.sleep(5000);
        System.out.println("-----------m2-----------");
        LockSupport.unpark(t);
    }
}
