package uoc.ds.pr;

import java.util.Comparator;
import java.util.Date;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.adt.sequential.StackArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.IteratorArrayImpl;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.util.DSArray;
import uoc.ds.pr.util.DSLinkedList;
import uoc.ds.pr.util.FiniteLinkedList;
import uoc.ds.pr.util.OrderedVector;


public class ShippingLineImpl implements ShippingLine {
    private DSArray<String,Ship> ships;
    private DSArray<String,Route> routes;
    private DSLinkedList<Client> clients;
    private DSLinkedList<Voyage> voyages;
    private OrderedVector<Client> mostTravelerClients;
    private OrderedVector<Route> mostTraveledRoute;

    // TODO: Implementar constructor
    public ShippingLineImpl() {
        ships = new DSArray<>(MAX_NUM_SHIPS);
        routes = new DSArray<>(MAX_NUM_ROUTES);
        clients = new DSLinkedList<>();
        voyages = new DSLinkedList<>();
        mostTravelerClients = new OrderedVector<>(MAX_CLIENTS, new Comparator<Client>() {
            @Override
            public int compare(Client client1, Client client2) {
                return client1.getReservations().size() - client2.getReservations().size();
            }
        });

        mostTraveledRoute = new OrderedVector<>(MAX_NUM_ROUTES, new Comparator<Route>() {
            @Override
            public int compare(Route route1, Route route2) {
                return route1.numVoyages() - route2.numVoyages();
            }
        });
    }

    // TODO: Implementar métodos y excepciones
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
    public void addVoyage(String id, Date departureDt, Date arrivalDt, String idShip, String idRoute) throws ShipNotFoundException, RouteNotFoundException {
        // Buscamos el buque
        Ship ship = getShip(idShip);
        // Si el buque no existe, lanzamos la excepción
        if (ship == null)
            throw new ShipNotFoundException();

        // Buscamos el trayecto
        Route route = getRoute(idRoute);
        // Si la travesía no existe, lanzamos la excepción
        if (route == null)
            throw new RouteNotFoundException();

        // Añadimos la travesía a la lista encadenda
        Voyage voyage = new Voyage(id, departureDt, arrivalDt, ship, route);
        voyages.insertEnd(voyage);
        // También lo añadimos a la lista encadenada de travesías del trayecto
        route.addVoyage(voyage);
        // Actualizamos el vector ordenado de trayectos más realizados
        mostTraveledRoute.update(route);
    }

    @Override
    public void reserve(String[] clients, String idVoyage, AccommodationType accommodationType, String idVehicle, double price) throws ClientNotFoundException, VoyageNotFoundException, ParkingFullException, NoAcommodationAvailableException, MaxExceededException, ReservationAlreadyExistsException {
        // Creamos una lista encadenada de clientes
        DSLinkedList<Client> clientsReservation = new DSLinkedList<>();

        // Añadimos a la lista encadenada los objetos Client buscandolo por su id con getClient()
        for (int i = 0; i < clients.length; i++) {
            Client client = getClient(clients[i]);
            // Si el cliente no existe, lanzamos la excepción
            if (client == null)
                throw new ClientNotFoundException();

            // Comprobamos si el cliente ha hecho ya una reserva en esta travesía
            Iterator<Reservation> iterator = client.getReservations().values();
            while(iterator.hasNext()) {
                Reservation reservation = iterator.next();

                // Si el cliente ya ha hecho la reserva, lanzamos la excepción
                if (reservation.getVoyage().getId().equals(idVoyage))
                    throw new ReservationAlreadyExistsException();
            }

            // Añadimos el cliente a la lista encadenada de clientes de la reserva
            clientsReservation.insertEnd(client);
        }

        // Buscamos la travesía por su id
        Voyage voyage = getVoyage(idVoyage);
        // Si la travesía no existe, lanzamos la excepción
        if (voyage == null)
            throw new VoyageNotFoundException();

        Reservation reservation = new Reservation(clientsReservation, voyage, accommodationType, idVehicle, price);

        // Añadimos la reserva a la FiniteLinkedList que le toque
        switch (accommodationType) {
            case ARMCHAIR:
                FiniteLinkedList<Reservation> armChairsReservations = voyage.getArmChairsReservations();
                for (int i = 0; i < clientsReservation.size(); i++) {
                    // Si no hay más butacas disponibles, lanzamos la excepción
                    if (armChairsReservations.isFull())
                        throw new NoAcommodationAvailableException();

                    // Añadimos la reserva a la FiniteLinkedList de reservas de butacas
                    voyage.getArmChairsReservations().insertEnd(reservation);
                }
                break;

            case CABIN2:
                // Si no hay más cabinas disponibles, lanzamos la excepción
                if (voyage.getCabin2Reservations().isFull())
                    throw new NoAcommodationAvailableException();

                // Si se supera el límite de la cabina, lanzamos la excepción
                if (clientsReservation.size() > MAX_PEOPLE_CABIN2)
                    throw new MaxExceededException();

                // Añadimos la reserva a la FiniteLinkedList de las cabinas
                voyage.getCabin2Reservations().insertEnd(reservation);
                break;

            case CABIN4:
                // Si no hay más cabinas disponibles, lanzamos la excepción
                if (voyage.getCabin4Reservations().isFull())
                    throw new NoAcommodationAvailableException();

                // Si se supera el límite de la cabina, lanzamos la excepción
                if (clientsReservation.size() > MAX_PEOPLE_CABIN4)
                    throw new MaxExceededException();

                // Añadimos la reserva a la FiniteLinkedList de las cabinas
                voyage.getCabin4Reservations().insertEnd(reservation);
                break;
        }

        // Comprobamos si la reserva tiene vehículo
        if (idVehicle != null) {
            StackArrayImpl<Reservation> reservationStackArray = voyage.getVehicleReservations();
            // Si el parking está lleno, lanzamos la excepción
            if (reservationStackArray.isFull())
                throw new ParkingFullException();

            // Añadimos la reserva al StackArray de reservas de vehículos
            reservationStackArray.push(reservation);
        }

        // Añadimos la reserva en la LinkedList de reservas del cliente (o clientes, si hay más de uno)
        Iterator<Client> clientIterator = clientsReservation.values();
        while (clientIterator.hasNext()) {
            Client client = clientIterator.next();

            DSLinkedList<Reservation> clientReservations = client.getReservations();
            clientReservations.insertEnd(reservation);
        }

        // Añadimos la reserva en la LinkedList de la travesía
        DSLinkedList<Reservation> voyageReservations = voyage.getReservations();
        voyageReservations.insertEnd(reservation);
    }

    @Override
    public void load(String idClient, String idVoyage, Date dt) throws LoadingAlreadyException, ClientNotFoundException, VoyageNotFoundException, ReservationNotFoundException {
        // Buscamos el cliente
        Client client = getClient(idClient);
        // Si el cliente no existe, lanzamos la excepción
        if (client == null)
            throw new ClientNotFoundException();

        // Buscamos la travesía
        Voyage voyage = getVoyage(idVoyage);
        // Si la travesía no existe, lanzamos la excepción
        if (voyage == null)
            throw new VoyageNotFoundException();

        // Comprobamos si la reserva existe
        boolean reservationExists = false, hasLoaded = false;
        Iterator<Reservation> iterator = client.reservations();
        while (iterator.hasNext() && !reservationExists) {
            Reservation reservation = iterator.next();

            if (reservation.getVoyage().getId().equals(idVoyage)) {
                reservationExists = true;
                hasLoaded = reservation.hasLoaded();

                // Comprobamos si ha embarcado o no
                if (hasLoaded)
                    throw new LoadingAlreadyException();
                else
                    reservation.setHasLoaded(true);
            }
        }

        // Si no existe la reserva, lanzamos la excepción
        if (!reservationExists)
            throw new ReservationNotFoundException();
    }

    @Override
    public Iterator<Reservation> unload(String idVoyage) throws VoyageNotFoundException {
        Voyage voyage = getVoyage(idVoyage);
        // Si la travesía no existe, lanzamos la excepción
        if (voyage == null)
            throw new VoyageNotFoundException();

        // El viaje ha terminado, por lo tanto, contamos que los clientes han realizado el viaje
        Iterator<Reservation> iterator = voyage.getReservations().values();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            Iterator<Client> clientsIterator = reservation.clients();
            while (clientsIterator.hasNext()) {
                // Actualizamos el vector ordenado de clientes más viajeros
                mostTravelerClients.update(clientsIterator.next());
            }
        }

        StackArrayImpl<Reservation> vehicleReservationsStackArray = voyage.getVehicleReservations();
        // Creamos el array para crear el iterador que se devolverá
        Reservation[] unloadVehicleReservationsArray = new Reservation[vehicleReservationsStackArray.size()];

        int i=0;
        while (!vehicleReservationsStackArray.isEmpty()) {
            // Vamos borrando de la pila los vehiculos y los añadimos al array
            Reservation reservation = vehicleReservationsStackArray.pop();
            unloadVehicleReservationsArray[i] = reservation;
            i++;
        }

        return new IteratorArrayImpl<>(unloadVehicleReservationsArray, vehicleReservationsStackArray.size(), 0);
    }

    // TODO: Se tiene que implementar?
    @Override
    public int unloadTime(String idVehicle, String idVoyage) throws LandingNotDoneException, VoyageNotFoundException, VehicleNotFoundException {
        Voyage voyage = getVoyage(idVoyage);
        // Si la travesía no existe, lanzamos la excepción
        if (voyage == null)
            throw new VoyageNotFoundException();

        return 0;
    }

    @Override
    public Iterator<Reservation> getClientReservations(String idClient) throws NoReservationException {
        Client client = getClient(idClient);
        Iterator<Reservation> clientReservations = client.getReservations().values();

        // Si el cliente no tiene reservas lanzamos la excepción
        if (!clientReservations.hasNext())
            throw new NoReservationException();

        return clientReservations;
    }

    @Override
    public Iterator<Reservation> getVoyageReservations(String idVoyage) throws NoReservationException {
        Voyage voyage = getVoyage(idVoyage);
        Iterator<Reservation> voyageReservations = voyage.getReservations().values();

        // Si la travesía no tiene reservas, lanzamos la excepción
        if (!voyageReservations.hasNext())
            throw new NoReservationException();

        return voyageReservations;
    }

    @Override
    public Iterator<Reservation> getAccommodationReservations(String idVoyage, AccommodationType accommodationType) throws NoReservationException {
        Voyage voyage = getVoyage(idVoyage);
        Iterator<Reservation> accomodationReservations = null;

        switch (accommodationType) {
            case ARMCHAIR:
                if (voyage.getArmChairsReservations().isEmpty())
                    throw new NoReservationException();

                accomodationReservations = voyage.getArmChairsReservations().values();
                break;

            case CABIN2:
                if (voyage.getCabin2Reservations().isEmpty())
                    throw new NoReservationException();

                accomodationReservations = voyage.getCabin2Reservations().values();
                break;

            case CABIN4:
                if (voyage.getCabin4Reservations().isEmpty())
                    throw new NoReservationException();

                accomodationReservations = voyage.getCabin4Reservations().values();
                break;
        }

        return accomodationReservations;
    }

    @Override
    public Client getMostTravelerClient() throws NoClientException {
        if (mostTravelerClients.isEmpty())
            throw new NoClientException();

        // Devolvemos el primer elemento del vector ordenado, ya que será el cliente más viajero
        return mostTravelerClients.first();
    }

    @Override
    public Route getMostTraveledRoute() throws NoRouteException {
        if (mostTraveledRoute.isEmpty())
            throw new NoRouteException();

        // Devolvemos el primer elemento del vector ordenado, ya que será el trayecto más realizado
        return mostTraveledRoute.first();
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
