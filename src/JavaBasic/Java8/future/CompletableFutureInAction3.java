package JavaBasic.Java8.future;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 18:17
 *   @Description :
 *
 */
public class CompletableFutureInAction3 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            return t;
        });

//        CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
//                .thenApply(CompletableFutureInAction3::multiply).whenComplete((v, t)->{
//                    Optional.of(v).ifPresent(System.out::println);
//        });

        // create 5 threads to use completableFuture and require data async
        List<Integer> productIDs = Arrays.asList(1,2,3,4,5);
        List<Double> result = productIDs.stream().map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executor))
                .map(future -> future.thenApply(CompletableFutureInAction3::multiply))
                .map(CompletableFuture::join).collect(toList());

        System.out.println(result);
    }

    private static double multiply(double value){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 10d;
    }

    private static double queryProduction(int i){
        return CompletableFutureInAction1.get();
    }
}
