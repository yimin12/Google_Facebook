package Object_Orient_Design.Design_Pattern.ResponibleChain;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 14:41
 *   @Description :
 *
 */
public class Client {

    public static void main(String[] args) {
        Leader a = new Director("The shy");
        Leader b = new Manager("Rookie");
        Leader c = new ViceGeneralManager("Uzi");
        Leader d = new GeneralManager("Yimin");
        a.setNextLeader(b);
        b.setNextLeader(c);
        c.setNextLeader(d);

        LeaveRequest req = new LeaveRequest("Tom", 15, "S11 is coming");
        a.handleRequest(req);
    }
}
