package parkingLot.stratergies.fee;

import parkingLot.entities.parking.ParkingTicket;

public interface FeeStratergy {
    public double calculateFee(ParkingTicket parkingTicket);
}
