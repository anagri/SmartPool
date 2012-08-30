package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.persistence.dao.RouteDao;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class RouteServiceTest {

    @Mock
    RouteDao routeDao = mock(RouteDao.class);

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        when(routeDao.getCarpoolNameListByLocation("Diamond District")).thenReturn(Arrays.asList("carpool-1"));
    }

    @Test
    public void shouldReturnAllCarpoolNameWithLocation() throws Exception {
        RouteService routeService = new RouteService(routeDao);
        routeService.getCarpoolNameList("Diamond District");

        verify(routeDao).getCarpoolNameListByLocation("Diamond District");
    }
}
