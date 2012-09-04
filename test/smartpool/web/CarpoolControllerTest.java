package smartpool.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.domain.Status;
import smartpool.service.BuddyService;
import smartpool.service.CarpoolBuilder;
import smartpool.service.CarpoolService;
import smartpool.service.RouteService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarpoolControllerTest {

    private CarpoolController carpoolController;
    @Mock
    private CarpoolService carpoolService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private BuddyService buddyService;
    @Mock
    private RouteService routeService;

    private ModelMap model;
    private Carpool expectedCarpool = CarpoolBuilder.CARPOOL_1;

    private ArrayList<Carpool> defaultCarpools;
    private final Buddy testBuddy = new Buddy("testBuddy");


    private List<String> defaultRouteLocations;

    @Before
    public void setUp() throws Exception {
        carpoolController = new CarpoolController(carpoolService,buddyService, routeService);
        when(carpoolService.getByName("carpool")).thenReturn(expectedCarpool);
        model = new ModelMap();

        defaultCarpools = new ArrayList<Carpool>() {{
            add(expectedCarpool);
        }};
        when(carpoolService.findAllCarpoolsByLocation("Diamond District")).thenReturn(defaultCarpools);

        when(buddyService.getCurrentBuddy(request)).thenReturn(testBuddy);

        when(request.getParameter("query")).thenReturn("Diamond District");

        defaultRouteLocations = Arrays.asList("Diamond District");
    }

    @Test
    public void shouldRedirectToViewCarpool() throws Exception {
        assertEquals(carpoolController.viewCarpool("carpool", model, request), "carpool/view");
    }

    @Test
    public void shouldHaveCarpoolInstanceInModel() throws Exception {
        carpoolController.viewCarpool("carpool", model, request);
        Carpool carpoolActual = (Carpool) model.get("carpool");

        assertEquals(expectedCarpool, carpoolActual);
    }

    @Test
    public void shouldSearchForCarpool() {
        carpoolController.searchByLocation(model, request);
        List<Carpool> searchResult = (List<Carpool>) model.get("searchResult");
        assertThat(searchResult, hasItems(expectedCarpool));
    }

    @Test
    public void shouldRedirectToViewSearchCarpool() throws Exception {
        assertThat(carpoolController.searchByLocation(model, request), equalTo("carpool/search"));
    }

    @Test
    public void shouldRedirectToCreateCarpool() throws Exception {
        assertThat(carpoolController.create(), equalTo("carpool/create"));
    }

    @Test
    public void shouldRedirectToViewCarpoolWhenPostedOnCreate(){
        assertThat(carpoolController.create(new Carpool("name"), "15/06/2012", "10:00", "18:00", model,request),equalTo("redirect:/carpool/name"));
    }

    @Test
    public void shouldInsertIntoDBWhenPostedOnCreate() throws Exception {
        Carpool carpool = new Carpool("name");
        carpoolController.create(carpool,"15/06/2012", "10:00", "18:00", model,request);
        verify(carpoolService).insert(carpool);
        assertThat(carpool.getStatus(),equalTo(Status.PENDING));
    }

    @Test
    public void shouldDisplayAllCarpoolsIfQueryIsNull() {
        when(carpoolService.findAllCarpoolsByLocation(null)).thenReturn(defaultCarpools);
        when(request.getParameter("query")).thenReturn(null);
        carpoolController.searchByLocation(model, request);
        assertThat((ArrayList<Carpool>) model.get("searchResult"), is(defaultCarpools));
    }

    @Test
    public void shouldAddCurrentBuddyToCarpoolWhileCreating() throws Exception {
        Carpool carpool = new Carpool("name");
        carpoolController.create(carpool, "15/06/2012", "10:00","18:00", model,request);
        assertThat(carpool.getBuddies().contains(testBuddy),equalTo(true));
    }

    @Test
    public void shouldGetAllRoutePoints() throws Exception {
        when(routeService.getAllLocation()).thenReturn(defaultRouteLocations);
        when(request.getParameter("query")).thenReturn(null);
        carpoolController.searchByLocation(model, request);
        assertThat((List<String>) model.get("routePoints"), is(defaultRouteLocations));
    }

}
