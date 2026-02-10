package parkingLot.stratergies.fee;

import java.util.Map;

import parkingLot.entities.parking.ParkingTicket;
import parkingLot.entities.vehicle.VehiceleSize;

public class VehicleBasedFeeStratergy implements FeeStratergy{
    Map<VehiceleSize,Double> Hourly_rate= Map.of(
        VehiceleSize.SMALL,10.0,
        VehiceleSize.MEDIUM,20.0,
        VehiceleSize.LARGE,30.0
    );
    
    @Override
    public double calculateFee(ParkingTicket ticket){
     long duration = ticket.getExitTimeStamp() - ticket.getEntryTime();
     long hours = (duration/(1000*60*60))+1;
     return Hourly_rate.get(ticket.getVehicle().getSize()) *hours;
    }
}
