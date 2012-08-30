package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.CarpoolDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.internal.matchers.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarpoolServiceTest {

    private CarpoolService carpoolService;
    Carpool carpoolExpected;

    @Mock
    CarpoolDao carpoolDao;

    @Mock
    RouteService routeService;

    @Before
    public void setUp() {
        initMocks(this);
        carpoolService = new CarpoolService(carpoolDao, routeService);
        carpoolExpected = CarpoolBuilder.CARPOOL_1;
    }

    @Test
    public void shouldFindCarpoolByName() {
        String carpoolName = "carpool-1";
        carpoolService.getByName(carpoolName);
        verify(carpoolDao).get(carpoolName);
        assertNotNull(carpoolService.findCarpoolByName(carpoolName));
    }


    @Test
    public void shouldNotFindAnyCarpool() {
        String carpoolName = "carpool-na";

        assertNull(carpoolService.getByName(carpoolName));
    }

    @Test
    public void shouldFindCarpoolsByLocation() {
        when(routeService.getCarpoolNameList("Diamond District")).thenReturn(Arrays.asList("carpool-1"));
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("Diamond District");
        assertThat(carpools, hasItems(carpoolExpected));
    }

    @Test
    public void shouldNotFindCarpoolsByInvalidLocation() {
        when(routeService.getCarpoolNameList("Invalid Location")).thenReturn(new ArrayList<String>());
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("Invalid Location");
        assertThat(carpools.size(), is(0));
    }

    @Test
    public void shouldReturnAllCarpoolsWhenSearchStringIsBlank() throws Exception {
        when(carpoolDao.selectAllCarpools()).thenReturn(Arrays.asList(new Carpool()));
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("");
        assertThat(carpools.size(),is(1));
    }

    @Test
    public void shouldInsertIntoDatabase(){
        carpoolService.insert(new Carpool("name"));
        verify(carpoolDao).insert(new Carpool("name"));
    }
}
