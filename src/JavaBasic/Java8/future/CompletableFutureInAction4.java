package JavaBasic.Java8.future;

import java.util.concurrent.CompletableFuture;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 18:28
 *   @Description :
 *
 */
public class CompletableFutureInAction4 {

    public static void main(String[] args) throws InterruptedException {
        /*CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .whenComplete((v, t) -> System.out.println(v));*/
       /* CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .whenComplete((v, t) -> System.out.println(v))
                .thenRun(System.out::println); // 如果上面已经打印过，则后面流内不再有信息，只打印一个换行

        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
                .thenAccept(System.out::println);

        // 让一个线程回调函数与另外一个线程回调函数进行结果结合(现实中不一定是相加，这里是相加)
        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)
                .thenAccept(System.out::println);*/

        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2)->{
                    System.out.println(r1);
                    System.out.println(r2);
                    System.out.println(r1 + r2);
                });
        Thread.sleep(1000L);
    }
}
