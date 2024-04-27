package uoc.ds.pr.model;

import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.util.DSLinkedList;

public class Client {
    private String id;
    private String name;
    private String surname;
    private DSLinkedList<Reservation> reservations;

    public Client(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        reservations = new DSLinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public DSLinkedList<Reservation> getReservations() {
        return reservations;
    }

    public Iterator<Reservation> reservations() {
        return reservations.values();
    }
}
