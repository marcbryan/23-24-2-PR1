package uoc.ds.pr.model;

public class Route {
    private String id;
    private String beginningPort;
    private String arrivalPort;

    public Route(String id, String beginningPort, String arrivalPort) {
        this.id = id;
        this.beginningPort = beginningPort;
        this.arrivalPort = arrivalPort;
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
}
