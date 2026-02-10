package parkingLot.stratergies.parking;

import java.util.List;
import java.util.Optional;

import parkingLot.entities.parking.ParkingFloor;
import parkingLot.entities.parking.ParkingSpot;
import parkingLot.entities.vehicle.Vehicle;

public interface ParkingStratergy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);

}
