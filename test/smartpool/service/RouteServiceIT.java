package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import smartpool.persistence.dao.RouteDao;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RouteServiceIT {

    private RouteService routeService;

    @Before
    public void setUp() throws Exception {
        routeService = new RouteService(new RouteDao());
    }

    @Test
    public void shouldGetCarpoolNamesFromDatabaseByLocation() throws Exception {
        List<String> carpoolNamesActual = routeService.getCarpoolNameList("Diamond District");
        List<String> carpoolNamesExpected = Arrays.asList("carpool-1");

        assertEquals(carpoolNamesExpected, carpoolNamesActual);
    }

    @Test
    public void shouldGetAllLocationsFromDatabase() throws Exception {
        assertEquals(8, routeService.getAllLocation().size());
    }
}
