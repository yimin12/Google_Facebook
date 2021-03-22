package MultiThreading.DesignPattern.FuturePattern;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 15:57
 *   @Description :
 *
 */
public class Driver {

    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();
        Data data = futureClient.request("zhangsan");
        System.out.println("Request successfully");

        System.out.println("Doing another thing");

        String res = data.getRequest();
        System.out.println("result = " + res);
    }
}
