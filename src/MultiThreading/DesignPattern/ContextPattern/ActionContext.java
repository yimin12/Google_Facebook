package MultiThreading.DesignPattern.ContextPattern;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 16:55
 *   @Description :
 *
 */
public class ActionContext {

    private final static ThreadLocal<Context> THREAD_LOCAL = ThreadLocal.withInitial(()->new Context());

    private static class ContextHolder{
        private final static ActionContext ACTION_CONTEXT = new ActionContext();
    }

    public static ActionContext getActionContext(){
        return ContextHolder.ACTION_CONTEXT;
    }

    public Context getContext(){
        return THREAD_LOCAL.get();
    }
}
