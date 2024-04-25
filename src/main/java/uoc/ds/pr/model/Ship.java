package uoc.ds.pr.model;

public class Ship {
    private String id;
    private String name;
    private int nArmChairs;
    private int nCabins2;
    private int nCabins4;
    private int nParkingSlots;
    private int unLoadTimeInMinutes;

    public Ship(String id, String name, int nArmChairs, int nCabins2, int nCabins4, int nParkingSlots, int unLoadTimeInMinutes) {
        this.id = id;
        this.name = name;
        this.nArmChairs = nArmChairs;
        this.nCabins2 = nCabins2;
        this.nCabins4 = nCabins4;
        this.nParkingSlots = nParkingSlots;
        this.unLoadTimeInMinutes = unLoadTimeInMinutes;
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

    public int getnArmChairs() {
        return nArmChairs;
    }

    public void setnArmChairs(int nArmChairs) {
        this.nArmChairs = nArmChairs;
    }

    public int getnCabins2() {
        return nCabins2;
    }

    public void setnCabins2(int nCabins2) {
        this.nCabins2 = nCabins2;
    }

    public int getnCabins4() {
        return nCabins4;
    }

    public void setnCabins4(int nCabins4) {
        this.nCabins4 = nCabins4;
    }

    public int getnParkingSlots() {
        return nParkingSlots;
    }

    public void setnParkingSlots(int nParkingSlots) {
        this.nParkingSlots = nParkingSlots;
    }

    public int getUnLoadTimeInMinutes() {
        return unLoadTimeInMinutes;
    }

    public void setUnLoadTimeInMinutes(int unLoadTimeInMinutes) {
        this.unLoadTimeInMinutes = unLoadTimeInMinutes;
    }
}
