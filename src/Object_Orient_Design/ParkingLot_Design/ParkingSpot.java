package Object_Orient_Design.ParkingLot_Design;


/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/14 0:05
 *   @Description :
 *
 */
public class ParkingSpot {
    private Vehicle vehicle;
    private VehicleSize spotSize;
    private int row;
    private int spotNumber;
    private Level level;

    public ParkingSpot(Level level, int row, int spotNumber, VehicleSize size){
        this.level = level;
        this.row = row;
        this.spotNumber = spotNumber;
        this.spotSize = size;
    }

    public boolean isAvailable(){
        return vehicle == null;
    }

    public boolean canFitVehicle(Vehicle vehicle){
        return isAvailable() && vehicle.canFitInSpot(this);
    }

    public boolean park(Vehicle vehicle){
        if(!canFitVehicle(vehicle)){
            this.vehicle = vehicle;
        }
        this.vehicle = vehicle;
        vehicle.parkInSpot(this);
        return true;
    }

    public void removeVehicle(){
        level.spotFreed();
        vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public int getRow() {
        return row;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public Level getLevel() {
        return level;
    }

    public void print() {
        if (vehicle == null) {
            if (spotSize == VehicleSize.Compact) {
                System.out.print("c");
            } else if (spotSize == VehicleSize.Large) {
                System.out.print("l");
            } else if (spotSize == VehicleSize.Motorcycle) {
                System.out.print("m");
            }
        } else {
            vehicle.print();
        }
    }
}
