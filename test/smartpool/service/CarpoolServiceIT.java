package smartpool.service;

import org.junit.Test;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.BuddyDao;
import smartpool.persistence.dao.CarpoolBuddyDao;
import smartpool.persistence.dao.CarpoolDao;
import smartpool.persistence.dao.RouteDao;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CarpoolServiceIT {
    @Test
    public void shouldGetCarpoolsFromDBWhenSearchByLocation() throws Exception {
        CarpoolService carpoolService = new CarpoolService(new CarpoolDao(), new BuddyDao(), new RouteDao(), new CarpoolBuddyDao());
        List<Carpool> allCarpoolsByLocation = carpoolService.findAllCarpoolsByLocation("Sony Centre");

        assertEquals("carpool-1", allCarpoolsByLocation.get(0).getName());
        assertEquals(2, allCarpoolsByLocation.size());
    }

    @Test
    public void shouldFilterCarpoolsFromPickupPoints() {
        CarpoolService carpoolService = new CarpoolService(new CarpoolDao(), new BuddyDao(), new RouteDao(), new CarpoolBuddyDao());
        List<Carpool> allCarpoolsByLocation = carpoolService.findAllCarpoolsByLocation("Kundalahalli");
        assertEquals("carpool-1", allCarpoolsByLocation.get(0).getName());
    }
}
