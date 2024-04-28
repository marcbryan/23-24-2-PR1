package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.StackArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.util.DSLinkedList;
import uoc.ds.pr.util.FiniteLinkedList;

import java.util.Date;

public class Voyage {
    private String id;
    private Date departureDt;
    private Date arrivalDt;
    private Ship ship;
    private Route route;
    private DSLinkedList<Reservation> reservations;
    private FiniteLinkedList<Reservation> armChairsReservations;
    private FiniteLinkedList<Reservation> cabin2Reservations;
    private FiniteLinkedList<Reservation> cabin4Reservations;
    private StackArrayImpl<Reservation> vehicleReservations;
    private StackArrayImpl<Reservation> parkingLots;

    public Voyage(String id, Date departureDt, Date arrivalDt, Ship ship, Route route) {
        this.id = id;
        this.departureDt = departureDt;
        this.arrivalDt = arrivalDt;
        this.ship = ship;
        this.route = route;

        // Inicializamos
        reservations = new DSLinkedList<>();
        // con la capacidad máxima
        armChairsReservations = new FiniteLinkedList<>(ship.getnArmChairs());
        cabin2Reservations = new FiniteLinkedList<>(ship.getnCabins2());
        cabin4Reservations = new FiniteLinkedList<>(ship.getnCabins4());
        vehicleReservations = new StackArrayImpl<>(ship.getnParkingSlots());
        parkingLots = new StackArrayImpl<>(ship.getnParkingSlots());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDepartureDt() {
        return departureDt;
    }

    public void setDepartureDt(Date departureDt) {
        this.departureDt = departureDt;
    }

    public Date getArrivalDt() {
        return arrivalDt;
    }

    public void setArrivalDt(Date arrivalDt) {
        this.arrivalDt = arrivalDt;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public DSLinkedList<Reservation> getReservations() {
        return reservations;
    }

    public FiniteLinkedList<Reservation> getArmChairsReservations() {
        return armChairsReservations;
    }

    public FiniteLinkedList<Reservation> getCabin2Reservations() {
        return cabin2Reservations;
    }

    public FiniteLinkedList<Reservation> getCabin4Reservations() {
        return cabin4Reservations;
    }

    public StackArrayImpl<Reservation> getVehicleReservations() {
        return vehicleReservations;
    }

    public StackArrayImpl<Reservation> getParkingLots() {
        return parkingLots;
    }

    public int getAvailableArmChairs() {
        return armChairsReservations.length() - armChairsReservations.size();
    }

    public int getAvailableCabin2() {
        return cabin2Reservations.length() - cabin2Reservations.size();
    }

    public int getAvailableCabin4() {
        return cabin4Reservations.length() - cabin4Reservations.size();
    }

    public int getAvailableParkingSlots() {
        return ship.getnParkingSlots() - vehicleReservations.size();
    }

    public int numParkingLots() {
        return parkingLots.size();
    }

    public boolean alreadyParked(String idVehicle) {
        Iterator<Reservation> iterator = parkingLots.values();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getIdVehicle().equals(idVehicle))
                return true;
        }

        return false;
    }
}
