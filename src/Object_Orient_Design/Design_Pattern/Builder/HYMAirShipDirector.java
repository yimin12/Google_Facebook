package Object_Orient_Design.Design_Pattern.Builder;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 12:28
 *   @Description :
 *
 */
public class HYMAirShipDirector implements AirShipDirector{

    private AirShipBuilder builder;

    public HYMAirShipDirector(AirShipBuilder builder){
        this.builder = builder;
    }

    @Override
    public AirShip directAirShip() {
        // generate the composition
        Engine e = builder.buildEngine();
        OrbitalModule o = builder.buildOrbitalModule();
        EscapeTower et = builder.buildEscapeTower();

        // composite
        AirShip ship = new AirShip();
        ship.setEngine(e);
        ship.setOrbitalModule(o);
        ship.setEscapeTower(et);
        return ship;
    }
}
