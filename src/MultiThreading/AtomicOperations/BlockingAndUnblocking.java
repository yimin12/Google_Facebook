package MultiThreading.AtomicOperations;

import java.util.concurrent.atomic.AtomicInteger;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/16 17:37
 *   @Description :
 *      Non-Blocking with explicit lock and Blocking Algorithm with implicit lock
 */
public class BlockingAndUnblocking {

    private final static CompareAndSetLock lock = new CompareAndSetLock();
    public static void main(String[] args) {

        for(int i = 0; i < 5; i ++){
            new Thread(){
                @Override
                public void run() {
                    try {
//                        blockingAlgorithm();
                        nonBlockingAlgorithm();
                    } catch (InterruptedException | GetLockException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    // implicit with JVM monitor
    private static void blockingAlgorithm() throws InterruptedException {
        synchronized (BlockingAndUnblocking.class){
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        }
    }

    // explicit with CAS
    private static void nonBlockingAlgorithm() throws GetLockException, InterruptedException{
        try{
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        } finally {
            lock.unlock();
        }
    }
}

// Use CAS way to create a lock
class CompareAndSetLock{
    private final AtomicInteger value = new AtomicInteger(0); // Only contain 1 and 0

    private Thread curHolder;

    public void tryLock() throws GetLockException{
        boolean success = value.compareAndSet(0, 1);
        if(!success){
            // if update fail, throw exception
            throw new GetLockException("Get the Lock failed");
        } else {
            curHolder = Thread.currentThread();
        }
    }

    public void unlock(){
        if( 0 == value.get()){
            return; // this lock is owned by the other thread
        }
        if(Thread.currentThread() == curHolder){
            value.compareAndSet(1, 0);
        }
    }
}