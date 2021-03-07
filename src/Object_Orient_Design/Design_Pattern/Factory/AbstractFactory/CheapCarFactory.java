package Object_Orient_Design.Design_Pattern.Factory.AbstractFactory;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/25 23:18
 *   @Description :
 *
 */
public class CheapCarFactory implements CarFactory{
    @Override
    public Engine createEngine() {
        return new CheapEngine();
    }

    @Override
    public Seat createSeat() {
        return new CheapSeat();
    }

    @Override
    public Tyre createTyre() {
        return new CheapTyre();
    }
}
