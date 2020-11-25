package MultiThreading.MyThreadPool.Exception;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/26 23:40
 *   @Description :
 *
 */
public class RunnableDenyException extends RuntimeException{

    public RunnableDenyException(String msg){
        super(msg);
    }

}
