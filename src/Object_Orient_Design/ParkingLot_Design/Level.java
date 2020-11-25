package Object_Orient_Design.ParkingLot_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/14 0:16
 *   @Description :
 *
 */
public class Level {

    private int floor;
    private ParkingSpot[] spots;
    private int availableSpots = 0;
    private int SPOTS_PRE_ROW;

    public Level(int floor, int num_rows, int spots_per_row){
        this.floor = floor;
        this.SPOTS_PRE_ROW = spots_per_row;
        int numberSpots = 0;
        this.spots = new ParkingSpot[num_rows * spots_per_row];
        for(int row = 0; row < num_rows; row++){
            for(int spot = 0; spot < spots_per_row/4; spot++){
                VehicleSize sz = VehicleSize.Motorcycle;
                spots[numberSpots] = new ParkingSpot(this, row, numberSpots, sz);
            }
            for(int spot = spots_per_row / 4; spot < spots_per_row/4*3; spot++){
                VehicleSize sz = VehicleSize.Compact;
                spots[numberSpots] = new ParkingSpot(this, row, numberSpots, sz);
            }
            for(int spot = spots_per_row/4*3; spot < spots_per_row; spot++){
                VehicleSize sz = VehicleSize.Large;
                spots[numberSpots] = new ParkingSpot(this, row, spots_per_row, sz);
            }
        }
        availableSpots = numberSpots;
    }

    public boolean parkVehicle(Vehicle vehicle){
        if(availableSpots() < vehicle.getSpotsNeeded()){
            return false;
        }
        int spotNumber = findAvalilableSpots(vehicle);
        if(spotNumber < 0){
            return false;
        }
        return parkStartingAtSpot(spotNumber, vehicle);
    }

    private int findAvalilableSpots(Vehicle vehicle){
        int spotsNeeded = vehicle.getSpotsNeeded();
        int lastRow = -1;
        int spotsFound = 0;
        for(int i = 0; i < spots.length; i++){
            ParkingSpot spot = spots[i];
            if(lastRow != spot.getRow()){
                spotsFound = 0;
                lastRow = spot.getRow();
            }
            if(spot.canFitVehicle(vehicle)){
                spotsFound++;
            } else {
                spotsFound = 0;
            }
            if(spotsFound == spotsNeeded){
                return i - (spotsNeeded - 1); // index of spot
            }
        }
        return -1;
    }

    private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle){
        vehicle.clearSpots();
        boolean success = true;
        for(int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++){
            success &= spots[i].park(vehicle);
        }
        availableSpots -= vehicle.spotsNeeded;
        return success;
    }

    public void spotFreed(){
        availableSpots++;
    }
    public int availableSpots(){
        return availableSpots;
    }
    public void print() {
        int lastRow = -1;
        for (int i = 0; i < spots.length; i++) {
            ParkingSpot spot = spots[i];
            if (spot.getRow() != lastRow) {
                System.out.print("  ");
                lastRow = spot.getRow();
            }
            spot.print();
        }
    }

}
