package MultiThreading.Synchronizer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/17 15:19
 *   @Description :
 *
 */
public class CountDownLatchExample1 {

    private static Random random = new Random(System.currentTimeMillis());

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        int[] data = query();
        for(int i = 0; i < data.length; i ++){
            executor.execute(new SimpleRunnable(data, i, countDownLatch));
        }
        countDownLatch.await();
//        executor.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("All threads have finished");
        executor.shutdown();

    }

    static class SimpleRunnable implements Runnable{
        private final int[] data;
        private final int index;
        private final CountDownLatch countDownLatch;
        SimpleRunnable(int[] data, int index, CountDownLatch countDownLatch){
            this.data = data;
            this.index = index;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = data[index];
            if(value % 2 == 0){
                data[index] = value * 2;
            } else {
                data[index] = value * 10;
            }
            System.out.println(Thread.currentThread().getName() + " finished");
            countDownLatch.countDown();
        }
    }

    private static int[] query(){
        return new int[]{1,2,3,4,5,6,7,8,9,10};
    }

}
