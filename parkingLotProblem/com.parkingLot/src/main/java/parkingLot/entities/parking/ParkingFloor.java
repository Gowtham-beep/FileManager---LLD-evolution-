package parkingLot.entities.parking;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import parkingLot.entities.vehicle.VehiceleSize;
import parkingLot.entities.vehicle.Vehicle;

public class ParkingFloor {
    private final int floorNumber;
    private Map<String,ParkingSpot> spots;

    public ParkingFloor(int floorNumber,Map<String,ParkingSpot> spots){
        this.floorNumber = floorNumber;
        this.spots = new ConcurrentHashMap<>(spots);
    }

    public void addSpot(ParkingSpot spot){
        spots.put(spot.getSpotId(), spot);
    }

    public Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle){
        return spots.values().stream()
        .filter(spot -> spot.isAvailabel() && spot.canFitVehicle(vehicle))
        .sorted(Comparator.comparing(ParkingSpot:: getSpotSize))
        .findFirst();
    }

    public void displayAvailability(){
        System.out.printf("--- Floor %d Availability ---\n", floorNumber);
        Map<VehiceleSize,Long> availableSpots = spots.values().stream()
        .filter(ParkingSpot::isAvailabel)
        .collect(Collectors.groupingBy(ParkingSpot::  getSpotSize,Collectors.counting()));

        for(VehiceleSize size : VehiceleSize.values()){
             System.out.printf("  %s spots: %d\n", size, availableSpots.getOrDefault(size, 0L));
        }
    }

}
