package MultiThreading.DesignPattern.ContextPattern;


/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 17:02
 *   @Description :
 *
 */
public class ExecutionTask implements Runnable{

    private QueryAction dbAction = new QueryFromDBAction();
    private QueryAction httpAction = new QueryFormatHttpAction();

    @Override
    public void run() {
        final Context context = ActionContext.getActionContext().getContext();
        dbAction.execute();
        System.out.println("企业 id 查询成功。");

        httpAction.execute();
        System.out.println("企业名称查询成功。");
        System.out.println("企业 id 为：" + context.getId() + "，企业名称为：" + context.getName());
    }
}
