package Object_Orient_Design.Design_Pattern.ResponibleChain;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 14:12
 *   @Description :
 *
 */
public abstract class Leader {

    protected String name;
    protected Leader nextLeader; // work similarly with linked list

    public Leader(String name) {
        super();
        this.name = name;
    }

    public void setNextLeader(Leader leader){
        this.nextLeader = leader;
    }

    public abstract void handleRequest(LeaveRequest request);
}
