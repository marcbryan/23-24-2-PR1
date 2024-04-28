package uoc.ds.pr.model;

// TODO: Revisar esta clase, posiblemente se deba extender de otra superclase
public class ParkingReservation extends Reservation {
    private int unLoadTimeInMinutes;

    public ParkingReservation(Reservation reservation, int unLoadTimeInMinutes) {
        super(reservation.getClients(), reservation.getVoyage(), reservation.getAccommodationType(), reservation.getIdVehicle(), reservation.getPrice());
        this.unLoadTimeInMinutes = unLoadTimeInMinutes;
    }

    public int getUnLoadTimeInMinutes() {
        return unLoadTimeInMinutes;
    }

    public void setUnLoadTimeInMinutes(int unLoadTimeInMinutes) {
        this.unLoadTimeInMinutes = unLoadTimeInMinutes;
    }
}
