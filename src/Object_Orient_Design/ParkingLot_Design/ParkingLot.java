package Object_Orient_Design.ParkingLot_Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/17 18:25
 *   @Description :
 *
 */
public class ParkingLot {
    private Level[] levels;
    private int NUM_LEVELS;

    public ParkingLot(int n, int num_rows, int spots_per_row){
        NUM_LEVELS = n;
        levels = new Level[NUM_LEVELS];
        for(int i = 0; i < NUM_LEVELS; i++){
            levels[i] = new Level(i, num_rows, spots_per_row);
        }
    }
    public boolean parkVehicle(Vehicle vehicle){
        for(int i = 0; i < levels.length; i++){
            if(levels[i].parkVehicle(vehicle)){
                return true;
            }
        }
        return false;
    }

    public void unParkVehicle(Vehicle vehicle){
        vehicle.clearSpots();
    }

    public void print() {
        for (int i = 0; i < levels.length; i++) {
            System.out.print("Level" + i + ": ");
            levels[i].print();
            System.out.println("");
        }
        System.out.println("");
    }

}
