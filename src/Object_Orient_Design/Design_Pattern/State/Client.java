package Object_Orient_Design.Design_Pattern.State;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 23:32
 *   @Description :
 *
 */
public class Client {

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.setState(new FreeState());
        hotel.setState(new BookedState());
    }
}
