package MultiThreading.DesignPattern.FuturePattern;

import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 15:52
 *   @Description :
 *
 */
public class RealData implements Data{

    private String result;

    public RealData(String queryStr){
        System.out.println("this is an " + queryStr + " Search, and consuming a lot of time");
        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("Get the result");
        this.result = "Theshy";
    }

    @Override
    public String getRequest() {
        return result;
    }
}
