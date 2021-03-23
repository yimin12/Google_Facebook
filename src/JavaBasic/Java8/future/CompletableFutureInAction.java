package JavaBasic.Java8.future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 16:30
 *   @Description :
 *
 */
public class CompletableFutureInAction {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        // ways to create CompletableFuture, 1: use new
       /* CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(()->{
            try{
                Thread.sleep(10000L);
                future.complete(1000d);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        }).start();
        System.out.println("..............");
        future.whenComplete((v, t) -> {
            System.out.println(v);
            t.printStackTrace();
        });*/

        // second way to create CompletableFuture, create it with daemon thread, it will terminate when main thread terminate
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(CompletableFutureInAction::get);
        future.whenComplete((v, t)->{
            System.out.println(v);
            t.printStackTrace();
        });





        long start = System.currentTimeMillis();
        List<Double> doubles = Arrays.asList(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble());
//        List<CompletableFuture<Double>> futures =
    }

    private static double get(){
        try{
            Thread.sleep(10000L);
            double v = random.nextDouble();
            System.out.println(v);
            return v;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
