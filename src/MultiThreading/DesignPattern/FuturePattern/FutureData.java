package MultiThreading.DesignPattern.FuturePattern;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 15:55
 *   @Description :
 *
 */
public class FutureData implements Data{

    private RealData realData;
    private boolean isReady = false; // whether it is onloaded completely

    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        this.isReady = true;
        notify();
    }

    @Override
    public synchronized String getRequest() {
        while(!isReady){
            try{
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        return this.realData.getRequest();
    }
}
