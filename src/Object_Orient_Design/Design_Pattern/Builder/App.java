package Object_Orient_Design.Design_Pattern.Builder;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 12:35
 *   @Description :
 *
 */
public class App {

    public static void main(String[] args) {
        AirShipDirector director = new HYMAirShipDirector(new HYMAirShipBuilder()); // Use the way you want to composite -> director and builder
        AirShip ship = director.directAirShip(); // compositing
        System.out.println(ship.getEngine().getName());
        ship.launch();
    }
}
