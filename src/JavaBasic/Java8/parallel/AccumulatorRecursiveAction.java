package JavaBasic.Java8.parallel;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 12:27
 *   @Description :
 *
 */
public class AccumulatorRecursiveAction extends RecursiveAction {

    private final int start, end;
    private final int[] data;
    private final int LIMIT=3;

    public AccumulatorRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        if((end - start) < LIMIT){
            for(int i = start; i < end; i ++){
                // base case
                AccumulatorHelper.accumulate(data[i]);
            }
        } else {
            int mid = start + (end - start) / 2;
            AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start, mid, data);
            AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid, end, data);
            left.fork();
            right.fork();
            left.join();
            right.join();
        }
    }

    static class AccumulatorHelper{

        private static final AtomicInteger result = new AtomicInteger(0);

        static void accumulate(int value){
            result.getAndAdd(value);
        }

        public static int getResult(){
            return result.get();
        }

        static void rest(){
            result.set(0);
        }
    }

}


