package JavaBasic.Java8.parallel;

import java.util.concurrent.RecursiveTask;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 12:07
 *   @Description :
 *
 */
public class AccumulatorRecursiveTask extends RecursiveTask<Integer> {


    private final int start, end;
    private final int[] data;
    private final int LIMIT = 3;

    public AccumulatorRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }



    @Override
    protected Integer compute() {
        // base case
        if((end - start) <= LIMIT){
            int result = 0;
            for(int i = start; i < end; i ++){
                result += data[i];
            }
            return result;
        }
        int mid = start + (end - start) / 2;
        AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start, mid, data);
        AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(mid, end, data);
        /**
         * fork 在OS里面就是开启一个子进程的意思，在这里就是开启一个子线程异步取处理left，当前线程处理右边。然后等待left执行完毕，两者一起累加返回给上级。
         */
        left.fork();
        Integer rightResult = right.compute();
        Integer leftResult = left.join();
        return rightResult + leftResult;
    }
}
