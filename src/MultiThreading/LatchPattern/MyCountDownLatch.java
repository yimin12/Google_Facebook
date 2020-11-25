package MultiThreading.LatchPattern;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 18:22
 *   @Description :
 *
 */
public class MyCountDownLatch {

    private final int latchNum;

    private int count;

    public MyCountDownLatch(int latchNum){
        this.latchNum = latchNum;
        this.count = latchNum;
    }

    public void countDown(){
        synchronized (this){
            this.count--;
            this.notify();
        }
    }

    public void await(){
        synchronized (this){
            if(count != 0){
                try{
                    this.wait();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
