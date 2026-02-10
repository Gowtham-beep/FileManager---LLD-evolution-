package parkingLot.stratergies.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import parkingLot.entities.parking.ParkingFloor;
import parkingLot.entities.parking.ParkingSpot;
import parkingLot.entities.vehicle.Vehicle;

public class FarthestParkingStratergy implements ParkingStratergy {

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors,Vehicle vehicle){
        List<ParkingFloor> reversedFloorList = new ArrayList<>(floors);
        Collections.reverse(reversedFloorList);

        for(ParkingFloor floor : reversedFloorList){
        Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
        if(spot.isPresent()){
            return spot;
        }
    }
    return Optional.empty();


    }
    
}
