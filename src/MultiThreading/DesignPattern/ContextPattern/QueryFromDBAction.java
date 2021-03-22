package MultiThreading.DesignPattern.ContextPattern;

import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:54
 *   @Description :
 *
 */
public class QueryFromDBAction implements QueryAction{


    @Override
    public void execute() {
        try{
            TimeUnit.SECONDS.sleep(1);;
            String id = "!23456" + Thread.currentThread().getId();
            ActionContext.getActionContext().getContext().setId(id);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
