package Contest.NineChapter.HighFreq.OOD;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private Level[] levels;
    private int NUM_LEVELS;

    public ParkingLot(int n, int num_rows, int spots_per_row){
        NUM_LEVELS = n;
        levels = new Level[NUM_LEVELS];
        for(int i = 0; i < NUM_LEVELS; i ++){
            levels[i] = new Level(i, num_rows, spots_per_row);
        }
    }

    public boolean parkVehicle(Vehicle vehicle){
        for(int i = 0; i < levels.length; i ++){
            if(levels[i].parkVehicle(vehicle)){
                return true;
            }
        }
        return false;
    }
}

class Car extends Vehicle{

    public Car() {
        spotsNeeded = 1;
        size = VehicleSize.COMPACT;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSpotSize() == VehicleSize.LARGE || spot.getSpotSize() == VehicleSize.COMPACT;
    }

    @Override
    public void print() {
        System.out.println("Car");
    }
}

class Bus extends Vehicle{

    public Bus() {
        spotsNeeded = 5;
        size = VehicleSize.LARGE;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSpotSize() == VehicleSize.LARGE;
    }

    @Override
    public void print() {
        System.out.println("Bus");
    }
}

class Motorcycle extends Vehicle{

    public Motorcycle() {
        spotsNeeded = 1;
        size = VehicleSize.MOTORCYCLE;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSpotSize() == VehicleSize.LARGE || spot.getSpotSize() == VehicleSize.COMPACT || spot.getSpotSize() == VehicleSize.MOTORCYCLE;
    }

    @Override
    public void print() {
        System.out.println("MotorCycle");
    }
}

enum VehicleSize{
    MOTORCYCLE, COMPACT, LARGE
}

class Level {
    private int floor;
    private ParkingSpot[] spots;
    private int availableSpots = 0, SPOT_PER_ROW;

    public Level(int floor, int num_rows, int spots_per_row){
        this.floor = floor;
        this.SPOT_PER_ROW = spots_per_row;
        int numberSpots = 0;
        this.spots = new ParkingSpot[num_rows * spots_per_row];
        for(int row = 0; row < num_rows; row ++){
            for(int spot = 0; spot < num_rows/4; spot++){
                VehicleSize sz = VehicleSize.MOTORCYCLE;
                spots[numberSpots ++] = new ParkingSpot(this, row, numberSpots, sz);
            }
            for(int spot = spots_per_row / 4; spot < spots_per_row / 4 * 3; spot++){
                VehicleSize sz = VehicleSize.COMPACT;
                spots[numberSpots ++] = new ParkingSpot(this, row, numberSpots, sz);
            }
            for(int spot = spots_per_row / 4 * 3; spot < spots_per_row; spot ++){
                VehicleSize sz = VehicleSize.LARGE;
                spots[numberSpots ++] = new ParkingSpot(this, row, numberSpots, sz);
            }
        }
        availableSpots = numberSpots;
    }

    public boolean parkVehicle(Vehicle vehicle){
        if(availableSpots() < vehicle.getSpotsNeeded()){
            return false;
        }
        int spotNumber = findAvailableSpots(vehicle);
        if(spotNumber < 0) return false;
        return parkStartingAtSpot(spotNumber, vehicle);
    }

    private int findAvailableSpots(Vehicle vehicle){
        int spotsNeeded = vehicle.spotsNeeded;
        int lastRow = -1;
        int spotsFound = 0;
        for(int i = 0; i < spots.length; i ++){
            ParkingSpot spot = spots[i];
            if(lastRow != spot.getRow()){
                spotsFound = 0;
                lastRow = spot.getRow();
            }
            if(spot.canFitVehicle(vehicle)){
                spotsFound ++;
            } else {
                spotsFound = 0;
            }
            if(spotsFound == spotsNeeded){
                return i - (spotsNeeded - 1);
            }
        }
        return -1;
    }

    private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle){
        vehicle.clearSpots();
        boolean success = true;
        for(int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i ++){
            success &= spots[i].park(vehicle);
        }
        availableSpots -= vehicle.spotsNeeded;
        return success;
    }

    public void freeSpot(){
        availableSpots ++;
    }

    public int availableSpots(){
        return availableSpots;
    }

    public void print(){
        int lastRow = -1;
        for(int i = 0; i < spots.length; i ++){
            ParkingSpot spot = spots[i];
            if(spot.getRow() != lastRow){
                System.out.println(" ");
                lastRow = spot.getRow();
            }
            spot.print();
        }
    }
}

class ParkingSpot{

    private Vehicle vehicle;
    private VehicleSize spotSize;
    private int row, spotNumber;
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

    public boolean canFitVehicle(Vehicle vehicle) {
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
        level.freeSpot();
        vehicle = null;
    }

    public Vehicle getVehicle(){
        return this.vehicle;
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

    public void print(){
        if(vehicle == null) {
            if(spotSize == VehicleSize.COMPACT){
                System.out.println("C");
            } else if(spotSize == VehicleSize.LARGE){
                System.out.println("L");
            } else if(spotSize == VehicleSize.MOTORCYCLE){
                System.out.println("M");
            }
        } else {
            vehicle.print();
        }
    }
}

abstract class Vehicle {

    protected int spotsNeeded;
    protected VehicleSize size;
    protected String licensePlate;

    protected List<ParkingSpot> parkingSpots = new ArrayList<>();

    public void parkInSpot(ParkingSpot spot){
        parkingSpots.add(spot);
    }

    public void clearSpots(){
        for(int i = 0; i < parkingSpots.size(); i ++){
            parkingSpots.get(i).removeVehicle();
        }
        parkingSpots.clear();
    }

    public abstract boolean canFitInSpot(ParkingSpot spot);
    public abstract void print();

    public int getSpotsNeeded(){
        return spotsNeeded;
    }

    public VehicleSize getSize() {
        return size;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
}