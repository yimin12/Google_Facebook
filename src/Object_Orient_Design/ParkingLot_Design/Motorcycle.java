package Object_Orient_Design.ParkingLot_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 18:36
 *   @Description :
 *
 */
public class Motorcycle extends Vehicle{

    public Motorcycle(){
        spotsNeeded = 1;
        size = VehicleSize.Motorcycle;
    }

    @Override
    public int getSpotsNeeded() {
        return super.getSpotsNeeded();
    }

    @Override
    public VehicleSize getSize() {
        return super.getSize();
    }

    @Override
    public void parkInSpot(ParkingSpot spot) {
        super.parkInSpot(spot);
    }

    @Override
    public void clearSpots() {
        super.clearSpots();
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return true;
    }

    @Override
    public void print() {
        System.out.println("M");
    }
}
