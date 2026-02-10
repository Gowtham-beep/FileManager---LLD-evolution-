package parkingLot;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Optional;

import parkingLot.entities.parking.ParkingFloor;
import parkingLot.entities.parking.ParkingSpot;
import parkingLot.entities.parking.ParkingTicket;
import parkingLot.entities.vehicle.Bike;
import parkingLot.entities.vehicle.Car;
import parkingLot.entities.vehicle.Truck;
import parkingLot.entities.vehicle.VehiceleSize;
import parkingLot.entities.vehicle.Vehicle;
import parkingLot.stratergies.fee.VehicleBasedFeeStratergy;
import parkingLot.stratergies.parking.NearestParkingStratergy;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = createParkingLot();

        ParkingFloor floor1 = new ParkingFloor(1, Map.of());
        floor1.addSpot(new ParkingSpot("F1-S1", VehiceleSize.SMALL, false, null));
        floor1.addSpot(new ParkingSpot("F1-M1", VehiceleSize.MEDIUM, false, null));
        floor1.addSpot(new ParkingSpot("F1-L1", VehiceleSize.LARGE, false, null));

        ParkingFloor floor2 = new ParkingFloor(2, Map.of());
        floor2.addSpot(new ParkingSpot("F2-M1", VehiceleSize.MEDIUM, false, null));
        floor2.addSpot(new ParkingSpot("F2-M2", VehiceleSize.MEDIUM, false, null));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);
        parkingLot.setFeeStratergy(new VehicleBasedFeeStratergy());
        parkingLot.setParkingStratergy(new NearestParkingStratergy());

        System.out.println("\n--- Vehicle Entries ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle bike = new Bike("B-123");
        Vehicle car = new Car("C-456");
        Vehicle truck = new Truck("T-789");

        Optional<ParkingTicket> bikeTicketOpt = parkingLot.parkVehicle(bike);
        Optional<ParkingTicket> carTicketOpt = parkingLot.parkVehicle(car);
        Optional<ParkingTicket> truckTicketOpt = parkingLot.parkVehicle(truck);

        System.out.println("\n--- Availability after parking ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle car2 = new Car("C-999");
        Optional<ParkingTicket> car2TicketOpt = parkingLot.parkVehicle(car2);

        Vehicle bike2 = new Bike("B-000");
        Optional<ParkingTicket> failedBikeTicketOpt = parkingLot.parkVehicle(bike2);

        System.out.println("\n--- Vehicle Exits ---");
        if (carTicketOpt.isPresent()) {
            Optional<Double> feeOpt = parkingLot.unParkVehicle(car.getLisceneceNumber());
            feeOpt.ifPresent(fee -> System.out.printf("Car C-456 unparked. Fee: $%.2f%n", fee));
        }

        System.out.println("\n--- Availability after one car leaves ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        // Keep optional values referenced to make demo intent explicit.
        if (bikeTicketOpt.isEmpty() || truckTicketOpt.isEmpty() || car2TicketOpt.isEmpty() || failedBikeTicketOpt.isPresent()) {
            System.out.println("Some parking operations did not behave as expected.");
        }
    }

    private static ParkingLot createParkingLot() {
        try {
            Constructor<ParkingLot> constructor = ParkingLot.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Unable to initialize ParkingLot for demo", ex);
        }
    }
}
