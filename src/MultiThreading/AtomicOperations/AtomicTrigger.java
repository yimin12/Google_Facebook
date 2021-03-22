package MultiThreading.AtomicOperations;

import java.util.concurrent.atomic.AtomicBoolean;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/16 18:40
 *   @Description :
 *      Implement by AtomicBoolean
 */
public class AtomicTrigger {

    // use AtomicBoolean to simulate trigger, similar with volatile
    private final static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                while(flag.get()){
                    try{
                        Thread.sleep(1000);
                        System.out.println("I am working");
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Thread.sleep(5000);
        flag.set(false);
    }

}
