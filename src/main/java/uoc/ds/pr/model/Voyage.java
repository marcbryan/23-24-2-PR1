package uoc.ds.pr.model;

import uoc.ds.pr.util.DSLinkedList;

import java.util.Date;

public class Voyage {
    private String id;
    private Date departureDt;
    private Date arrivalDt;
    private String idShip;
    private String idRoute;
    private Ship ship;
    private Route route;
    private DSLinkedList<Reservation> reservations;

    public Voyage(String id, Date departureDt, Date arrivalDt, String idShip, String idRoute) {
        this.id = id;
        this.departureDt = departureDt;
        this.arrivalDt = arrivalDt;
        this.idShip = idShip;
        this.idRoute = idRoute;
        reservations = new DSLinkedList<>();
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

    public String getIdShip() {
        return idShip;
    }

    public void setIdShip(String idShip) {
        this.idShip = idShip;
    }

    public String getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(String idRoute) {
        this.idRoute = idRoute;
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

    public int getAvailableArmChairs() {
        // TODO: Modificar
        return ship.getnArmChairs();
    }

    public int getAvailableCabin2() {
        // TODO: Modificar
        return ship.getnCabins2();
    }

    public int getAvailableCabin4() {
        // TODO: Modificar
        return ship.getnCabins4();
    }

    public int getAvailableParkingSlots() {
        // TODO: Modificar
        return ship.getnParkingSlots();
    }
}
