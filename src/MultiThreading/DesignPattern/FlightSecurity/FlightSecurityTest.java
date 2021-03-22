package MultiThreading.DesignPattern.FlightSecurity;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/27 15:48
 *   @Description :
 *
 */
public class FlightSecurityTest {

    static class Passengers extends Thread{
        private final FlightSecurity flightSecurity;
        private final String idCard;
        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass){
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            flightSecurity.pass(boardingPass, idCard);
        }
    }

    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity, "A123456", "AF123456").start();
        new Passengers(flightSecurity, "B123456", "BF123456").start();
        new Passengers(flightSecurity, "C123456", "CF123456").start();

    }
}
