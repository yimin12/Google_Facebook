package Object_Orient_Design.Design_Pattern.ResponibleChain;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 13:20
 *   @Description :
 *
 */
public class Director extends Leader{

    public Director(String name){
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if(request.getLeaveDays() < 3) {
            System.out.println("Director approved" + request.getEmpName() + "for absence");
        } else {
            if(this.nextLeader != null){
                this.nextLeader.handleRequest(request);
            }
        }
    }


}
