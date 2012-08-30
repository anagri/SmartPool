package smartpool.service;

import org.junit.Test;
import smartpool.persistence.dao.RouteDao;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RouteServiceIT {
    @Test
    public void shouldGetCarpoolNamesFromDatabaseByLocation() throws Exception {
        RouteService routeService = new RouteService(new RouteDao());
        List<String> carpoolNamesActual = routeService.getCarpoolNameList("Diamond District");
        List<String> carpoolNamesExpected = Arrays.asList("carpool-1");

        assertEquals(carpoolNamesExpected, carpoolNamesActual);

    }
}
