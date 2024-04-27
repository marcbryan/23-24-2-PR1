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
        // Buscamos el buque y la ruta
        Ship ship = getShip(idShip);
        Route route = getRoute(idRoute);

        Voyage voyage = new Voyage(id, departureDt, arrivalDt, ship, route);
        voyages.insertEnd(voyage);
    }

    @Override
    public void reserve(String[] clients, String idVoyage, AccommodationType accommodationType, String idVehicle, double price) throws ClientNotFoundException, VoyageNotFoundException, ParkingFullException, NoAcommodationAvailableException, MaxExceededException, ReservationAlreadyExistsException {
        // Creamos una lista encadenada de clientes
        DSLinkedList<Client> clientsReservation = new DSLinkedList<>();

        // Añadimos a la lista encadenada los objetos Client buscandolo por su id con getClient()
        for (int i = 0; i < clients.length; i++) {
            Client client = getClient(clients[i]);
            clientsReservation.insertEnd(client);
        }

        // Buscamos la travesía por su id
        Voyage voyage = getVoyage(idVoyage);

        Reservation reservation = new Reservation(clientsReservation, voyage, accommodationType, idVehicle, price);


        // Añadimos la reserva en la LinkedList de la travesía
        DSLinkedList<Reservation> voyageReservations = voyage.getReservations();
        voyageReservations.insertEnd(reservation);

        // Añadimos la reserva a la FiniteLinkedList que le toque
        switch (accommodationType) {
            case ARMCHAIR:
                voyage.getArmChairsReservations().insertEnd(reservation);
                break;

            case CABIN2:
                voyage.getCabin2Reservations().insertEnd(reservation);
                break;

            case CABIN4:
                voyage.getCabin4Reservations().insertEnd(reservation);
                break;
        }

        // Añadimos la reserva en la LinkedList del cliente (o clientes, si hay más de uno)
        Iterator<Client> clientIterator = clientsReservation.values();
        while (clientIterator.hasNext()) {
            Client client = clientIterator.next();

            DSLinkedList<Reservation> clientReservations = client.getReservations();
            clientReservations.insertEnd(reservation);
        }
    }

    @Override
    public void load(String idClient, String idVoyage, Date dt) throws LoadingAlreadyException, ClientNotFoundException, VoyageNotFoundException, ReservationNotFoundException {
        Client client = getClient(idClient);
        Voyage voyage = getVoyage(idVoyage);
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
        Client client = getClient(idClient);
        return client.getReservations().values();
    }

    @Override
    public Iterator<Reservation> getVoyageReservations(String idVoyage) throws NoReservationException {
        Voyage voyage = getVoyage(idVoyage);
        return voyage.getReservations().values();
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
        return ships.get(id);
    }

    @Override
    public Route getRoute(String idRoute) {
        return routes.get(idRoute);
    }

    @Override
    public Client getClient(String id) {
        Iterator<Client> iterator = clients.values();

        while (iterator.hasNext()) {
            Client client = iterator.next();

            // Si encontramos el id en el cliente, lo devolvemos
            if (client.getId().equals(id))
                return client;
        }

        return null;
    }

    @Override
    public Voyage getVoyage(String id) {
        Iterator<Voyage> iterator = voyages.values();

        while (iterator.hasNext()) {
            Voyage voyage = iterator.next();

            // Si encontramos el id en la travesía, lo devolvemos
            if (voyage.getId().equals(id))
                return voyage;
        }

        return null;
    }

    @Override
    public int numShips() {
        return ships.size();
    }

    @Override
    public int numRoutes() {
        return routes.size();
    }

    @Override
    public int numClients() {
        return clients.size();
    }

    @Override
    public int numVoyages() {
        return voyages.size();
    }
}
