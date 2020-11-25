package Object_Orient_Design.ParkingLot_Design;

import java.util.ArrayList;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/14 0:02
 *   @Description :
 *
 */
public abstract class Vehicle {
    protected int spotsNeeded;
    protected VehicleSize size;
    protected String licensePlate;

    protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();

    public int getSpotsNeeded(){
        return spotsNeeded;
    }

    public VehicleSize getSize(){
        return size;
    }

    public void parkInSpot(ParkingSpot spot){
        parkingSpots.add(spot);
    }

    public void clearSpots(){
        for(int i = 0; i < parkingSpots.size(); i++){
            parkingSpots.get(i).removeVehicle();
        }
        parkingSpots.clear();
    }
    public abstract boolean canFitInSpot(ParkingSpot spot);
    public abstract void print();
}
