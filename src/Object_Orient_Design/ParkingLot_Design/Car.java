package Object_Orient_Design.ParkingLot_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 18:39
 *   @Description :
 *
 */
public class Car extends Vehicle {

    public Car(){
        spotsNeeded = 1;
        size = VehicleSize.Compact;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSpotSize() == VehicleSize.Large || spot.getSpotSize() == VehicleSize.Compact;
    }

    @Override
    public void print() {
        System.out.println("C");
    }
}


