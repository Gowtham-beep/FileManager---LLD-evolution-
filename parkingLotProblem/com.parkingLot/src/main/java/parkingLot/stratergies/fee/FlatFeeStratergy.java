package parkingLot.stratergies.fee;

import parkingLot.entities.parking.ParkingTicket;

public class FlatFeeStratergy  implements FeeStratergy{
    private final double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateFee(ParkingTicket ticket){
        long duration = ticket.getExitTimeStamp() - ticket.getEntryTime();
        long hours = (duration/(1000*60*60))+1;
        return hours*RATE_PER_HOUR;
    }
}
