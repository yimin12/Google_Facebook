package Object_Orient_Design.Design_Pattern.ResponibleChain;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 14:32
 *   @Description :
 *
 */
public class GeneralManager extends Leader{


    public GeneralManager(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if(request.getLeaveDays() < 30){
            System.out.println("General manager approved" + request.getEmpName() + "for absence");
        } else {
            System.out.println("Rejected");
        }
    }
}
