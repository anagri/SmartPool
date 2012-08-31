package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.BuddyDao;
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
    Carpool expectedCarpool;
    private List<Carpool> expectedCarpools;

    @Mock
    CarpoolDao carpoolDao;

    @Mock
    RouteService routeService;
    @Mock
    BuddyDao buddyDao;

    @Before
    public void setUp() {
        initMocks(this);
        carpoolService = new CarpoolService(carpoolDao, buddyDao, routeService);
        expectedCarpool = CarpoolBuilder.CARPOOL_1;
        expectedCarpools = Arrays.asList(CarpoolBuilder.CARPOOL_1);
    }

    @Test
    public void shouldFindCarpoolByName() {
        String carpoolName = "carpool-1";
        when(carpoolDao.get("carpool-1")).thenReturn(new Carpool());
        Carpool carpoolActual = carpoolService.findCarpoolByName(carpoolName);
        verify(carpoolDao).get(carpoolName);
        assertNotNull(carpoolActual);
    }


    @Test
    public void shouldNotFindAnyCarpool() {
        String carpoolName = "carpool-na";

        assertNull(carpoolService.getByName(carpoolName));
    }

    @Test
    public void shouldFindCarpoolsByLocation() {
        when(routeService.getCarpoolNameList("Diamond District")).thenReturn(Arrays.asList("carpool-1"));
        when(carpoolDao.get("carpool-1")).thenReturn(expectedCarpool);
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("Diamond District");
        assertThat(carpools, hasItems(expectedCarpool));
    }

    @Test
    public void shouldNotFindCarpoolsByInvalidLocation() {
        when(routeService.getCarpoolNameList("Invalid Location")).thenReturn(new ArrayList<String>());
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("Invalid Location");
        assertThat(carpools.size(), is(0));
    }

    @Test
    public void shouldReturnAllCarpoolsWhenSearchStringIsBlank() throws Exception {
        when(carpoolDao.selectAllCarpools()).thenReturn(expectedCarpools);
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("");
        assertThat(carpools, is(expectedCarpools));
    }

    @Test
    public void shouldReturnAllCarpoolsWhenSearchStringIsNull() throws Exception {
        when(carpoolDao.selectAllCarpools()).thenReturn(expectedCarpools);
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation(null);
        assertThat(carpools, is(expectedCarpools));
    }

    @Test
    public void shouldInsertIntoDatabase() {
        carpoolService.insert(new Carpool("name"));
        verify(carpoolDao).insert(new Carpool("name"));
    }

    @Test
    public void shouldUpdateBuddyWhileCreatingCarpool() throws Exception {
        Carpool carpool = new Carpool("name");
        ArrayList<Buddy> buddies = new ArrayList<Buddy>();
        Buddy testBuddy = new Buddy("testBuddy");
        buddies.add(testBuddy);
        carpool.setBuddies(buddies);
        carpoolService.insert(carpool);
        verify(buddyDao).addToCarpool(testBuddy, carpool);
    }
}
