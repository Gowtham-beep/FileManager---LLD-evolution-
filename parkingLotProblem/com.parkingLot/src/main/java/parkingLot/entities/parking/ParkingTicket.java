package parkingLot.entities.parking;


import java.util.UUID;

import parkingLot.entities.vehicle.Vehicle;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot Spot;
    private final long entryTimeStamp;
    private long exitTimeStamp;

    public ParkingTicket(Vehicle vehicle,ParkingSpot spot){
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.Spot = spot;
        this.entryTimeStamp = System.currentTimeMillis();
    }

    public String getTicketId(){
        return this.ticketId;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public ParkingSpot getSpot(){
        return this.Spot;
    }
    public long getEntryTime(){
        return this.entryTimeStamp;
    }

    public void setExitTimeStamp(){
        this.exitTimeStamp = System.currentTimeMillis();
    }

    public long getExitTimeStamp(){
        return this.exitTimeStamp;
    }
}
