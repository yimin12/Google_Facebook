package JavaBasic.Java8.future;

import java.util.concurrent.*;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 13:45
 *   @Description :
 *
 */
public class FutureInAction2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(()->{
            try{
                TimeUnit.MILLISECONDS.sleep(10000L);
                return "I am finished. ";
            } catch (InterruptedException e){
                return "I am Error";
            }
        });

        while(!future.isDone()){
            TimeUnit.MILLISECONDS.sleep(10L);
        }
        System.out.println(future.get());
        executorService.shutdown();
    }
}
