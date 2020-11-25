package Object_Orient_Design.ParkingLot_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 18:41
 *   @Description :
 *
 */
public class Bus extends Vehicle{

    public Bus() {
        spotsNeeded = 5;
        size = VehicleSize.Large;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSpotSize() == VehicleSize.Large;
    }

    @Override
    public void print() {
        System.out.println("B");
    }
}
