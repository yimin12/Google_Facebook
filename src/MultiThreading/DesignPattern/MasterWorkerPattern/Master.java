package MultiThreading.DesignPattern.MasterWorkerPattern;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:10
 *   @Description :
 *
 */
public class Master {

    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<>();

    private HashMap<String, Thread> workers = new HashMap<>();

    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    public Master(Worker worker, int workerCount){
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);

        for(int i = 0; i < workerCount; i++){
            this.workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    public void submitTask(Task task){
        this.workQueue.add(task);
    }

    public void execute(){
        for(Map.Entry<String, Thread> map : workers.entrySet()){
            map.getValue().start();
        }
    }

    public boolean isStop(){
        for(Map.Entry<String, Thread> map : workers.entrySet()){
            if(map.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    public int getResult(){
        int priceResult = 0;
        for(Map.Entry<String, Object> map : resultMap.entrySet()){
            priceResult += (int)map.getValue();
        }
        return priceResult;
    }
}
