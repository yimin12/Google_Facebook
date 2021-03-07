package Object_Orient_Design.Design_Pattern.Factory.AbstractFactory;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 23:04
 *   @Description :
 *
 */
public interface CarFactory {

    Engine createEngine();
    Seat createSeat();
    Tyre createTyre();

}
