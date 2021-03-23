package JavaBasic.Java8.parallel;

import java.util.concurrent.ForkJoinPool;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/22 12:32
 *   @Description :
 *
 */
public class ForkJoinPoolTest {

    private static int[] data = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) {
        System.out.println("result => " + cal());
        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer result = forkJoinPool.invoke(task);
        System.out.println("AccumulatorRecursiveTask >>" + result);

        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        System.out.println("AccumulatorRecursiveAction >>" + AccumulatorRecursiveAction.AccumulatorHelper.getResult());
    }

    private static int cal(){
        int result = 0;
        for(int i = 0; i < data.length; i ++){
            result += data[i];
        }
        return result;
    }
}
