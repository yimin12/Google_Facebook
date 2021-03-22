package MultiThreading.AtomicOperations;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/16 20:14
 *   @Description :
 *
 */
public class UnsafeTest {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
        // it will throw exception
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println(unsafe);
        // declare it with reflection
//        Unsafe unsafe = getUnsafe();
//        System.out.println(unsafe);

        // Compare the Efficiency of locking
        /**
         * Naive Counter:
         *  Counter result : 9970341 wrong
         *  Time consumption : 392
         * Synchronized Counter:
         *  Counter result : 10000000 right
         *  Time consumption : 754
         * ReentrantLock Counter:
         *  Counter result : 10000000 right
         *  Time consumption : 483
         * Atomic Counter:
         *  Counter result : 10000000 right
         *  Time consumption : 467
         * Cas Counter:
         *  Counter result : 10000000 right
         *  Time consumption : 1106
         */
        ExecutorService service = Executors.newFixedThreadPool(1000);
        Counter counter = new CasCounter();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i ++){
            service.submit(new CounterRunnable(counter, 10000));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS); // shutdown the thread-pool after 1 hour
        long end = System.currentTimeMillis();
        System.out.println("Counter result : " + counter.getCounter());
        System.out.println("Time consumption : " + (end - start));
    }

    // you can only acquire it reflection
    private static Unsafe getUnsafe(){
        try{
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            f.get(null);
            return (Unsafe) f.get(null);
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    interface Counter{
        void increment();
        long getCounter();
    }

    static class CounterRunnable implements Runnable{
        private final Counter counter;
        private final int num;

        public CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for(int i = 0; i < num; i ++){
                counter.increment();
            }
        }
    }

    // Different type of Counter implementation
    static class NaiveCounter implements Counter{
        private long counter = 0;
        @Override
        public void increment() {
            counter ++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class SyncCounter implements Counter{
        private long counter = 0;
        @Override
        public synchronized void increment() {
            counter ++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class LockCounter implements Counter{

        private final Lock lock = new ReentrantLock();
        private long counter = 0;
        @Override
        public void increment() {
            try{
                lock.lock();
                counter ++;
            } finally{
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class AtomicCounter implements Counter{

        private AtomicLong counter = new AtomicLong();
        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }

    static class CasCounter implements Counter{

        private volatile long counter = 0;
        private Unsafe unsafe;
        private long offset;

        CasCounter() throws NoSuchFieldException {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            long current = counter;
            while(!unsafe.compareAndSwapLong(this, offset, current, current + 1)){
                current = counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }
}
