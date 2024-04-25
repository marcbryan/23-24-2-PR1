package uoc.ds.pr.model;

import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.ShippingLine.AccommodationType;

public class Reservation {
    private String[] clients;
    private String idVoyage;
    private AccommodationType accommodationType;
    private String idVehicle;
    private double price;

    public Reservation(String[] clients, String idVoyage, AccommodationType accommodationType, String idVehicle, double price) {
        this.clients = clients;
        this.idVoyage = idVoyage;
        this.accommodationType = accommodationType;
        this.idVehicle = idVehicle;
        this.price = price;
    }

    public Iterator<Client> clients() {
        // TODO: Implementar
        return null;
    }

    public String[] getClients() {
        return clients;
    }

    public void setClients(String[] clients) {
        this.clients = clients;
    }

    public String getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(String idVoyage) {
        this.idVoyage = idVoyage;
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
}
