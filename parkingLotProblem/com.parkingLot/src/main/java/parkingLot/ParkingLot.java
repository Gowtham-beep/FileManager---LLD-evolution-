package parkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import parkingLot.entities.parking.ParkingFloor;
import parkingLot.entities.parking.ParkingSpot;
import parkingLot.entities.parking.ParkingTicket;
import parkingLot.entities.vehicle.Vehicle;
import parkingLot.stratergies.fee.FeeStratergy;
import parkingLot.stratergies.parking.ParkingStratergy;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String,ParkingTicket> activeTickets ;
    private FeeStratergy feeStratergy;
    private ParkingStratergy parkingStratergy;

    private ParkingLot( ){
        this.feeStratergy = feeStratergy;
        this.parkingStratergy = parkingStratergy;
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public synchronized ParkingLot getInstance(){
        if(instance == null){
            instance = new ParkingLot();
        }
        return instance;
    }
    public void addFloor(ParkingFloor floor){
        floors.add(floor);
    }
    public void setFeeStratergy(FeeStratergy feeStratergy){
        this.feeStratergy = feeStratergy;
    }
    public void setParkingStratergy(ParkingStratergy parkingStratergy){
        this.parkingStratergy = parkingStratergy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle){
        Optional<ParkingSpot> availableSpots = parkingStratergy.findSpot(floors, vehicle);
        if(availableSpots.isPresent()){
            ParkingSpot spot = availableSpots.get();
            spot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(vehicle,spot);
            activeTickets.put(vehicle.getLisceneceNumber(),ticket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLisceneceNumber(), spot.getSpotId(), ticket.getTicketId());
            return Optional.of(ticket);
        }
        return Optional.empty();
    }

    public Optional<Double> unParkVehicle(String liscenceNUmber){
        ParkingTicket ticket = activeTickets.remove(liscenceNUmber);
        if(ticket == null){
            System.out.println("Ticket not found");
            return Optional.empty();
        }
        ticket.setExitTimeStamp();
        ticket.getSpot().unParkVehicle();

        Double ParkingFee = feeStratergy.calculateFee(ticket);

        return Optional.of(ParkingFee);
    }
}
