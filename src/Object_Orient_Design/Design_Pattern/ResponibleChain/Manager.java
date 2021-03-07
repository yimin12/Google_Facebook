package Object_Orient_Design.Design_Pattern.ResponibleChain;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 14:32
 *   @Description :
 *
 */
public class Manager extends Leader{


    public Manager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if(request.getLeaveDays() < 10){
            System.out.println("Manager approved" + request.getEmpName() + "for absence");
        } else {
            if(this.nextLeader != null){
                this.nextLeader.handleRequest(request);
            }
        }
    }
}
