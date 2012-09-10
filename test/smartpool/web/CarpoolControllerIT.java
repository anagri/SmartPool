package smartpool.web;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.*;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolService;
import smartpool.service.JoinRequestService;
import smartpool.service.RouteService;
import smartpool.web.form.CreateCarpoolFormValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarpoolControllerIT {

    @Mock
    HttpServletRequest request;

    @Test
    public void shouldSearchForCarpool() {
        initMocks(this);
        CarpoolController carpoolController = new CarpoolController(new CarpoolService(new CarpoolDao(), new BuddyDao(), new RouteDao(), new CarpoolBuddyDao()), new JoinRequestService(new JoinRequestDao()),new BuddyService(), new RouteService(new RouteDao()), new CreateCarpoolFormValidator());
        ModelMap model = new ModelMap();
        request.setAttribute("query", "Sony Centre");
        carpoolController.searchByLocation(model, request);
        List<Carpool> searchResult = (List<Carpool>) model.get("searchResult");
        assertEquals(2, searchResult.size());
    }
}
