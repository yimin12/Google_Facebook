package Object_Orient_Design.Design_Pattern.Builder;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/26 12:20
 *   @Description :
 *
 */
public interface AirShipBuilder {

    Engine buildEngine();
    OrbitalModule buildOrbitalModule();
    EscapeTower buildEscapeTower();

}
