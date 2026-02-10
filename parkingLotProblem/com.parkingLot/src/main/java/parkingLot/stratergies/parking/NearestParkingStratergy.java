package parkingLot.stratergies.parking;

import java.util.List;
import java.util.Optional;

import parkingLot.entities.parking.ParkingFloor;
import parkingLot.entities.parking.ParkingSpot;
import parkingLot.entities.vehicle.Vehicle;

public class NearestParkingStratergy implements ParkingStratergy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle){
    for(ParkingFloor floor : floors){
        Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
        if(spot.isPresent()){
            return spot;
        }
    }
    return Optional.empty();
    }
}
