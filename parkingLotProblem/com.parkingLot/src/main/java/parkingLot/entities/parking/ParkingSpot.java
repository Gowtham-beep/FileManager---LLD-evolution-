package parkingLot.entities.parking;

import parkingLot.entities.vehicle.VehiceleSize;
import parkingLot.entities.vehicle.Vehicle;

public class ParkingSpot {
    private final String spotId;
    private final VehiceleSize spotSize;
    private Boolean isOccupied;
    private  Vehicle parkedVehicle;

    public ParkingSpot(String spotId,VehiceleSize spotSize,Boolean isOccupied,Vehicle parkedVehicle){
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public String getSpotId(){
        return this.spotId;
    }
    public VehiceleSize getSpotSize(){
        return this.spotSize;
    }
    public Boolean isAvailabel(){
        return !isOccupied;
    }
    public Vehicle getParkedVehicle(){
        return this.parkedVehicle;
    }
    public synchronized void parkVehicle(Vehicle vehicle){
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }
    public synchronized void unParkVehicle(){
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public Boolean canFitVehicle(Vehicle vehicle){
        if(isOccupied) return false;
        switch (vehicle.getSize()) {
            case SMALL:
                return spotSize == vehicle.getSize();
            case MEDIUM:
                return spotSize == vehicle.getSize();
            case LARGE:
                return spotSize == vehicle.getSize();
            default:
                return false;
        }
    }
}
