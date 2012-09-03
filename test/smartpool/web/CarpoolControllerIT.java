package smartpool.web;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.BuddyDao;
import smartpool.persistence.dao.CarpoolDao;
import smartpool.persistence.dao.RouteDao;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolService;
import smartpool.service.RouteService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CarpoolControllerIT {



    @Test
    public void shouldSearchForCarpool() {
        CarpoolController carpoolController = new CarpoolController(new CarpoolService(new CarpoolDao(), new BuddyDao(), new RouteService(new RouteDao())), new BuddyService(), new RouteService(new RouteDao()));
        ModelMap model = new ModelMap();
        carpoolController.searchByLocation("Sony Centre", model);
        List<Carpool> searchResult = (List<Carpool>) model.get("searchResult");
        assertEquals(2, searchResult.size());
    }
}
