package uoc.ds.pr;

import java.util.Date;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.util.DSArray;
import uoc.ds.pr.util.DSLinkedList;
import uoc.ds.pr.util.OrderedVector;


public class ShippingLineImpl implements ShippingLine {
    private DSArray<String,Ship> ships;
    private DSArray<String,Route> routes;
    private DSLinkedList<Client> clients;
    private DSLinkedList<Voyage> voyages;

    // TODO: Implementar constructor
    public ShippingLineImpl() {
        ships = new DSArray<>(MAX_NUM_SHIPS);
        routes = new DSArray<>(MAX_NUM_ROUTES);
        clients = new DSLinkedList<>();
        voyages = new DSLinkedList<>();
    }

    // TODO: Implementar metodos
    @Override
    public void addShip(String id, String name, int nArmChairs, int nCabins2, int nCabins4, int nParkingSlots, int unLoadTimeInMinutes) {
        Ship ship = new Ship(id, name, nArmChairs, nCabins2, nCabins4, nParkingSlots, unLoadTimeInMinutes);
        ships.put(ship.getId(), ship);
    }

    @Override
    public void addRoute(String id, String beginningPort, String arrivalPort) {
        Route route = new Route(id, beginningPort, arrivalPort);
        routes.put(route.getId(), route);
    }

    @Override
    public void addClient(String id, String name, String surname) {
        Client client = new Client(id, name, surname);
        clients.insertEnd(client);
    }

    @Override
    public void addVoyage(String id, Date departureDt, Date arrivalDt, String idShip, String idRoute) throws ShipNotFoundException, RouteNotFoundException, ParkingFullException, NoAcommodationAvailableException {
        Voyage voyage = new Voyage(id, departureDt, arrivalDt, idShip, idRoute);
        voyages.insertEnd(voyage);
    }

    @Override
    public void reserve(String[] clients, String idVoyage, AccommodationType accommodationType, String idVehicle, double price) throws ClientNotFoundException, VoyageNotFoundException, ParkingFullException, NoAcommodationAvailableException, MaxExceededException, ReservationAlreadyExistsException {
        Reservation reservation = new Reservation(clients, idVoyage, accommodationType, idVehicle, price);

    }

    @Override
    public void load(String idClient, String idVoyage, Date dt) throws LoadingAlreadyException, ClientNotFoundException, VoyageNotFoundException, ReservationNotFoundException {

    }

    @Override
    public Iterator<Reservation> unload(String idVoyage) throws VoyageNotFoundException {
        return null;
    }

    @Override
    public int unloadTime(String idVehicle, String idVoyage) throws LandingNotDoneException, VoyageNotFoundException, VehicleNotFoundException {
        return 0;
    }

    @Override
    public Iterator<Reservation> getClientReservations(String idClient) throws NoReservationException {
        return null;
    }

    @Override
    public Iterator<Reservation> getVoyageReservations(String idVoyage) throws NoReservationException {
        return null;
    }

    @Override
    public Iterator<Reservation> getAccommodationReservations(String idVoyage, AccommodationType accommodationType) throws NoReservationException {
        return null;
    }

    @Override
    public Client getMostTravelerClient() throws NoClientException {
        return null;
    }

    @Override
    public Route getMostTraveledRoute() throws NoRouteException {
        return null;
    }

    @Override
    public Ship getShip(String id) {
        return null;
    }

    @Override
    public Route getRoute(String idRoute) {
        return null;
    }

    @Override
    public Client getClient(String id) {
        return null;
    }

    @Override
    public Voyage getVoyage(String id) {
        return null;
    }

    @Override
    public int numShips() {
        return 0;
    }

    @Override
    public int numRoutes() {
        return 0;
    }

    @Override
    public int numClients() {
        return 0;
    }

    @Override
    public int numVoyages() {
        return 0;
    }
}
