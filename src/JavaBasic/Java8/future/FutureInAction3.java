package JavaBasic.Java8.future;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 13:52
 *   @Description :
 *
 */
public class FutureInAction3 {

    public static void main(String[] args) {

        Future<String> future = invoke(() -> {
            // callable.action
            try{
                TimeUnit.MILLISECONDS.sleep(10000L);
                return "I am finished. ";
            } catch (InterruptedException e){
                return "InterruptionException e";
            }
        });

        // register event
        future.setCompletable(new Completable<String>() {

            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println("error");
                cause.printStackTrace();
            }
        });
        System.out.println("...........");
        System.out.println(future.get());
        System.out.println(future.get());
    }

    private static <T> Future<T> invoke(Callable<T> callable){
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Future<T> future = new Future<T>(){
            private Completable<T> completable;

            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };
        Thread t = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);
                if(future.getCompletable() != null){
                    future.getCompletable().complete(value);
                }
            } catch (Throwable cause){
                if(future.getCompletable() != null){
                    future.getCompletable().exception(cause);
                }
            }
        });
        t.start();
        return future;
    }

    private interface Future<T>{
        T get();
        boolean isDone();
        void setCompletable(Completable<T> completable);
        Completable<T> getCompletable();
    }

    private interface Callable<T>{
        T action() throws InterruptedException;
    }

    private interface Completable<T>{
        void complete(T t);
        void exception(Throwable cause);
    }


}
