package uoc.ds.pr.model;

// TODO: Revisar esta clase, posiblemente se deba extender de otra superclase
public class ParkingReservation {
    private String idVehicle;
    private int unLoadTimeInMinutes;

    public ParkingReservation(String idVehicle, int unLoadTimeInMinutes) {
        this.idVehicle = idVehicle;
        this.unLoadTimeInMinutes = unLoadTimeInMinutes;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(String idVehicle) {
        this.idVehicle = idVehicle;
    }

    public int getUnLoadTimeInMinutes() {
        return unLoadTimeInMinutes;
    }

    public void setUnLoadTimeInMinutes(int unLoadTimeInMinutes) {
        this.unLoadTimeInMinutes = unLoadTimeInMinutes;
    }
}
