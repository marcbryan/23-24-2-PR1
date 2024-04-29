package uoc.ds.pr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.util.DateUtils;

public class AdditionalPR1Test {
    private ShippingLine theShippingLine;

    @Before
    public void setUp() throws Exception {
        this.theShippingLine = FactoryShippingLine.getShippingLine();
        Assert.assertEquals(7, this.theShippingLine.numShips());
        Assert.assertEquals(9, this.theShippingLine.numRoutes());
        Assert.assertEquals(18, this.theShippingLine.numClients());
        Assert.assertEquals(3, this.theShippingLine.numVoyages());
    }

    @After
    public void tearDown() {
        this.theShippingLine = null;
    }

    /**
     * Comprueba que no hay ningún trayecto más realizado, ya que no se han añadido travesías
     * @throws DSException
     */
    @Test
    public void checkNoRouteExceptionTest() throws DSException {
        // Creamos el ShippingLineImpl de nuevo
        theShippingLine = new ShippingLineImpl();

        // Añadimos un barco y un trayecto pero ninguna travesía
        theShippingLine.addShip("shipId4", "Norwegian Escape", 10, 2, 12, 12, 7 );
        theShippingLine.addRoute("routeId1", "Barcelona", "Civitavecchia");

        Assert.assertThrows(NoRouteException.class, () ->
                theShippingLine.getMostTraveledRoute());
    }

    /**
     * Comprueba si se ha llenado el parking
     * @throws DSException
     */
    @Test
    public void checkParkingFullTest() throws DSException {
        generateReservations();

        // Debe saltar la excepción, ya que solo hay 12 plazas de parking
        Assert.assertThrows(ParkingFullException.class, () ->
                theShippingLine.reserve(new String[]{"clientId13"}, "voyageId1", ShippingLine.AccommodationType.CABIN4, "3210 MDC", 300 ));
    }

    /**
     * Test para comprobar que el método unloadTime() de ShippingLineImpl funciona correctamente
     * @throws DSException
     */
    @Test
    public void unloadTimeTest() throws DSException {
        generateReservations();

        Assert.assertThrows(VoyageNotFoundException.class, () ->
                theShippingLine.unloadTime("1111 AAA", "XXXX"));

        // Embarque
        for (int i = 1; i <= 12; i++) {
            String clientId = "clientId" + i;
            theShippingLine.load(clientId, "voyageId1", DateUtils.createDate("30-07-2024 08:50:00"));
        }

        Assert.assertThrows(LandingNotDoneException.class, () ->
                theShippingLine.unloadTime("1234 ABC", "voyageId1"));

        // Desembarque
        theShippingLine.unload("voyageId1");

        Assert.assertThrows(VehicleNotFoundException.class, () ->
                theShippingLine.unloadTime("1111 AAA", "voyageId1"));

        int unloadTime = theShippingLine.unloadTime("2468 LMN", "voyageId1");
        Assert.assertEquals(7, unloadTime);
    }

    private void generateReservations() throws DSException {
        String[] vehicleIDs = { "1234 ABC", "2345 BCD", "3456 CDE", "4567 DEF", "5678 EFG", "6789 FGH", "7890 GHI", "8421 HIJ", "9512 IJK", "0975 JKL", "1357 KLM", "2468 LMN" };

        for (int i = 0; i < 12; i++) {
            String[] clients = {"clientId" + (i+1)};
            theShippingLine.reserve(clients, "voyageId1", ShippingLine.AccommodationType.CABIN4, vehicleIDs[i], 300 );
        }
    }
}
