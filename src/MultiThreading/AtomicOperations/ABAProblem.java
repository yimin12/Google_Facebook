package MultiThreading.AtomicOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/16 19:16
 *   @Description :
 *      CAS can not detect ABA problem, you can add time stamp(optimistic lock) to detect it
 *      AtomicRefferenceStamp is a good way to solve it
 */
public class ABAProblem {

    public static void main(String[] args) throws InterruptedException {
        // Test ABA problem
        AtomicReference<Simple> atomic = new AtomicReference<Simple>(new Simple("Alex", 12));
        System.out.println(atomic.get());
        boolean result = atomic.compareAndSet(new Simple("sdfs", 22), new Simple("sdfs",234) );
        System.out.println(result);

        // Solving the APA problem by using stamp
        AtomicStampedReference<Integer> atomicRef = new AtomicStampedReference<Integer>(100, 0); // init value and init stamp
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    boolean success = atomicRef.compareAndSet(100, 101, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println(success);
                    success = atomicRef.compareAndSet(101, 100, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println(success);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    int stamp = atomicRef.getStamp();
                    System.out.println("Before sleep : stamp = " + stamp);
                    TimeUnit.SECONDS.sleep(2);
                    boolean success = atomicRef.compareAndSet(100, 101, stamp, stamp + 1);
                    System.out.println(success);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }


    static class Simple{
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
