package JavaBasic.Java8.future;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 13:28
 *   @Description :
 *      Future concept is used to handle blocking and non-blocking problem
 */
public class FutureInAction {

    public static void main(String[] args) throws InterruptedException {
       /* // function use non-blocking algorithm
        Future<String> future = invoke(() -> {
            try{
                Thread.sleep(10000);
                return "I am finished.";
            } catch (InterruptedException e){
                return "Error";
            }
        });
        System.out.println(future.get());
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println(future.get());
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println(future.get());
        // .... non-blocking
        while(!future.isDone()){
            Thread.sleep(10);
        }
        System.out.println(future.get());*/

        // using blocking algorithm
        String value = block(() -> {
            try {
                Thread.sleep(10000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        System.out.println(value);

    }

    private static <T> T block(Callable<T> callable){
        return callable.action();
    }

    private static <T> Future<T> invoke(Callable<T> callable){
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread t = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            finished.set(true);
        });
        t.start();

        Future<T> future = new Future<T>(){

            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };
        return future;
    }

    private interface Future<T>{
        T get();
        boolean isDone();
    }

    private interface Callable<T>{
        T action();
    }
}
