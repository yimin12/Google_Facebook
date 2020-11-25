package MultiThreading.FuturePattern;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 15:54
 *   @Description :
 *
 */
public class FutureClient {

    public Data request(String queryStr){
        final FutureData futureData = new FutureData();
        new Thread(()->{
            RealData realData = new RealData(queryStr);
            futureData.setRealData(realData);
        }).start();
        return futureData;
    }
}
