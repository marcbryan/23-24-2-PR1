package uoc.ds.pr.model;

import uoc.ds.pr.util.DSLinkedList;

public class Route {
    private String id;
    private String beginningPort;
    private String arrivalPort;
    private DSLinkedList<Voyage> voyages;

    public Route(String id, String beginningPort, String arrivalPort) {
        this.id = id;
        this.beginningPort = beginningPort;
        this.arrivalPort = arrivalPort;
        voyages = new DSLinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeginningPort() {
        return beginningPort;
    }

    public void setBeginningPort(String beginningPort) {
        this.beginningPort = beginningPort;
    }

    public String getArrivalPort() {
        return arrivalPort;
    }

    public void setArrivalPort(String arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public void addVoyage(Voyage voyage) {
        voyages.insertEnd(voyage);
    }

    public int numVoyages() {
        return voyages.size();
    }

    @Override
    public String toString() {
        return beginningPort + "-" + arrivalPort;
    }
}
