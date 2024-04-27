package uoc.ds.pr.model;

import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.ShippingLine.AccommodationType;
import uoc.ds.pr.util.DSLinkedList;

public class Reservation {
    private DSLinkedList<Client> clients;
    private Voyage voyage;
    private AccommodationType accommodationType;
    private String idVehicle;
    private double price;
    private boolean hasLoaded = false;

    public Reservation(DSLinkedList<Client> clients, Voyage voyage, AccommodationType accommodationType, String idVehicle, double price) {
        this.clients = clients;
        this.voyage = voyage;
        this.accommodationType = accommodationType;
        this.idVehicle = idVehicle;
        this.price = price;
    }

    public Iterator<Client> clients() {
        return clients.values();
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(AccommodationType accommodationType) {
        this.accommodationType = accommodationType;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(String idVehicle) {
        this.idVehicle = idVehicle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean hasLoaded() {
        return hasLoaded;
    }

    public void setHasLoaded(boolean hasLoaded) {
        this.hasLoaded = hasLoaded;
    }

    public int numClients() {
        return clients.size();
    }

    public boolean hasParkingLot() {
        return (idVehicle != null);
    }
}
