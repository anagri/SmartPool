package smartpool.persistence.dao;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RouteDaoIT {
    @Test
    public void shouldInsertRoutePointInDB() throws Exception {
        RouteDao routeDao = new RouteDao();
        routeDao.insert("carpool-1","MG Road");
        List<String> carpoolNames = routeDao.getCarpoolNameListByLocation("MG Road");
        assertThat(carpoolNames.contains("carpool-1"), equalTo(true));
        routeDao.delete("carpool-1","MG Road");
    }
}
