package MultiThreading.MasterWorkerPattern;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:06
 *   @Description :
 *
 */
public class Worker implements Runnable {

    // Master should record the reference to every worker
    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue){
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap){
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while(true){
            Task task = this.workQueue.poll();
            if(null == task){
                break;
            }
            Object result = handle(task);
            this.resultMap.put(Integer.toString(task.getId()), result);
        }
    }

    private Object handle(Task task){
        Object result = null;
        try{
            TimeUnit.MILLISECONDS.sleep(500);
            result = task.getPrice();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
