package MultiThreading.ContextPattern;

import java.util.concurrent.TimeUnit;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:59
 *   @Description :
 *
 */
public class QueryFormatHttpAction implements QueryAction{

    private String getNameById(String id){
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        return "YIMIN HUANG " + Thread.currentThread().getName();
    }

    @Override
    public void execute() {
        try{
            TimeUnit.SECONDS.sleep(1);
            String id = ActionContext.getActionContext().getContext().getId();
            ActionContext.getActionContext().getContext().setName(getNameById(id));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
