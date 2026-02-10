# ParkingLot Prohecy

A Java-based object-oriented parking lot simulator that demonstrates strategy-based spot allocation and fee calculation.

## Overview

This project models a multi-floor parking lot with:
- Vehicle types: `Bike`, `Car`, `Truck`
- Spot sizes: `SMALL`, `MEDIUM`, `LARGE`
- Pluggable parking strategies (nearest/farthest)
- Pluggable fee strategies (flat/vehicle-based)
- Ticket-based parking/unparking flow

The implementation is focused on clean domain modeling and extensibility through interfaces.

## Project Structure

- `parkingLotProblem/com.parkingLot/src/main/java/parkingLot/ParkingLot.java`: Core parking lot service (park/unpark, ticket tracking).
- `parkingLotProblem/com.parkingLot/src/main/java/parkingLot/ParkingLotDemo.java`: Demo runner with sample floors, vehicles, and operations.
- `parkingLotProblem/com.parkingLot/src/main/java/parkingLot/entities/`: Domain entities (`Vehicle`, `ParkingSpot`, `ParkingFloor`, `ParkingTicket`).
- `parkingLotProblem/com.parkingLot/src/main/java/parkingLot/stratergies/`: Strategy interfaces and implementations for parking and fee logic.

## Design Highlights

- Uses Strategy Pattern for runtime-switchable behavior:
  - `ParkingStratergy` for spot selection
  - `FeeStratergy` for fee calculation
- Uses `ConcurrentHashMap` for active ticket tracking.
- Keeps floor-level spot lookup and availability reporting in `ParkingFloor`.

## Requirements

- Java 17+
- Maven 3.8+

## Build and Run

From the module root:

```bash
cd parkingLotProblem/com.parkingLot
mvn clean compile
java -cp target/classes parkingLot.ParkingLotDemo
```

## Run Tests

```bash
cd parkingLotProblem/com.parkingLot
mvn test
```

## Example Flow in Demo

1. Build a parking lot with two floors and multiple spot sizes.
2. Configure:
   - `NearestParkingStratergy`
   - `VehicleBasedFeeStratergy`
3. Park `Bike`, `Car`, and `Truck`.
4. Attempt additional parking when capacity is constrained.
5. Unpark a vehicle and compute fee from ticket duration.

## Extending the Project

- Add a new parking rule by implementing `ParkingStratergy`.
- Add a new pricing model by implementing `FeeStratergy`.
- Add new vehicle categories by extending `Vehicle` and mapping size/fee behavior.

## Notes

- Naming in the code uses `Stratergy`/`Vehicele` spellings consistently; keep this in mind when navigating classes.
- `ParkingLotDemo` uses reflection to create `ParkingLot` because the constructor is private and `getInstance()` is not static.
