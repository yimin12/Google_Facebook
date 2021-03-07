package Object_Orient_Design.Design_Pattern.ResponibleChain;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 14:32
 *   @Description :
 *
 */
public class ViceGeneralManager extends Leader{


    public ViceGeneralManager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if(request.getLeaveDays() < 20){
            System.out.println("Vice general manager approved" + request.getEmpName() + "for absence");
        } else {
            if(this.nextLeader != null){
                this.nextLeader.handleRequest(request);
            }
        }
    }
}
