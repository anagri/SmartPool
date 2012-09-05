package smartpool.persistence.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RouteDaoIT {

    private RouteDao routeDao;

    @Before
    public void setUp() throws Exception {
        routeDao = new RouteDao();
        routeDao.insert("carpool-1", "MG Road");
    }

    @Test
    public void shouldInsertRoutePointInDB() throws Exception {
        List<String> carpoolNames = routeDao.getCarpoolNameListByLocation("MG Road");
        assertThat(carpoolNames.contains("carpool-1"), equalTo(true));
    }

    @Test
    public void shouldGetRouteListForACarpool() throws Exception {
        ArrayList<String> routePoints = routeDao.getLocationsFor("carpool-1");
        assertThat(routePoints.contains("MG Road"),equalTo(true));
    }

    @After
    public void tearDown() throws Exception {
        routeDao.delete("carpool-1", "MG Road");
    }
}
