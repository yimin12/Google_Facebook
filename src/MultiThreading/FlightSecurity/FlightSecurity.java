package MultiThreading.FlightSecurity;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 15:46
 *   @Description :
 *      先模拟一个非线程安全的安检口类，旅客（线程）分别手持登机牌和身份证接受工作人员的检查
 */
public class FlightSecurity {

    private int count = 0;
    private String boardingPass = "null";
    private String idCard = "null";

    public synchronized void pass(String boardingPass, String idCard){
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check(){
        if(boardingPass.charAt(0) != idCard.charAt(0)){
            throw new RuntimeException("------Exception----------" + toString());
        }
    }

    @Override
    public String toString() {
        return "FlightSecurity{" +
                "count=" + count +
                ", boardingPass='" + boardingPass + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
