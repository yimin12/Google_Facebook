package MultiThreading.AtomicOperations;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/16 17:56
 *   @Description :
 *
 */
public class GetLockException extends Exception{

    public GetLockException(){
        super();
    }

    public GetLockException(String msg){
        super(msg);
    }
}
