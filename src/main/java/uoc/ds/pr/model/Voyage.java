package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.StackArrayImpl;
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

    public Route getRoute() {
        return route;
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

    public int getAvailableArmChairs() {
        return armChairsReservations.size();
    }

    public int getAvailableCabin2() {
        return cabin2Reservations.size();
    }

    public int getAvailableCabin4() {
        return cabin4Reservations.size();
    }

    public int getAvailableParkingSlots() {
        return vehicleReservations.size();
    }
}
