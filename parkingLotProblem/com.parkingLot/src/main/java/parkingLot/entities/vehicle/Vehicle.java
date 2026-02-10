package parkingLot.entities.vehicle;

public abstract class Vehicle {
    private String liscenceNumber;
    private VehiceleSize size;

    public Vehicle(String liscenceNUmber, VehiceleSize size){
        this.liscenceNumber = liscenceNUmber;
        this.size = size;
    }

    public String getLisceneceNumber(){
        return this.liscenceNumber;
    }

    public VehiceleSize getSize(){
        return this.size;
    }
}
