package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.persistence.dao.RouteDao;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class RouteServiceTest {

    @Mock
    RouteDao routeDao = mock(RouteDao.class);
    private RouteService routeService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        when(routeDao.getCarpoolNameListByLocation("Diamond District")).thenReturn(Arrays.asList("carpool-1"));
        routeService = new RouteService(routeDao);
    }

    @Test
    public void shouldReturnAllCarpoolNameWithLocation() throws Exception {
        routeService.getCarpoolNameList("Diamond District");

        verify(routeDao).getCarpoolNameListByLocation("Diamond District");
    }


    @Test
    public void shouldReturnAllLocationsFromDatabase() throws Exception {
        when(routeDao.getAllLocations()).thenReturn(Arrays.asList("Diamond District", "Mars"));
        assertEquals(2, routeService.getAllLocation().size());
    }
}
