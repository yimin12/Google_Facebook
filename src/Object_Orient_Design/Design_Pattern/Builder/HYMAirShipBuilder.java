package Object_Orient_Design.Design_Pattern.Builder;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 12:26
 *   @Description :
 *      StringBuilder, XML parser, JDOM: DomBuilder, SaxBuilder
 */
public class HYMAirShipBuilder implements AirShipBuilder{


    @Override
    public Engine buildEngine() {
        return new Engine("Yimin Engine");
    }

    @Override
    public OrbitalModule buildOrbitalModule() {
        return new OrbitalModule("Yimin OrbitalModule");
    }

    @Override
    public EscapeTower buildEscapeTower() {
        return new EscapeTower("Yimin EscapeTower");
    }
}
