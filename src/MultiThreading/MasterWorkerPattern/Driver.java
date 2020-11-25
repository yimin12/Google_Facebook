package MultiThreading.MasterWorkerPattern;

import java.util.Random;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:16
 *   @Description :
 *
 */
public class Driver {

    public static void main(String[] args) {
        Master master = new Master(new Worker(), 50);
        Random random = new Random();
        for(int i = 1; i <= 100; i++){
            Task t = new Task();
            t.setId(i);
            t.setName("Mission-"+i);
            t.setPrice(random.nextInt(1000));
            master.submitTask(t);
        }
        long start = System.currentTimeMillis();
        master.execute();
        while(true){
            if(master.isStop()){
                long end = System.currentTimeMillis();
                int priceResult = master.getResult();
                System.out.println("Final result is : " + priceResult + " and the execution time is " + (end - start));
                break;
            }
        }
    }
}
