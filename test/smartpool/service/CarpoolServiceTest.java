package smartpool.service;

import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.common.Constants;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.domain.CarpoolBuddy;
import smartpool.persistence.dao.BuddyDao;
import smartpool.persistence.dao.CarpoolBuddyDao;
import smartpool.persistence.dao.CarpoolDao;
import smartpool.persistence.dao.RouteDao;

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
    RouteDao routeDao;
    @Mock
    BuddyDao buddyDao;
    @Mock
    private CarpoolBuddyDao carpoolBuddyDao;

    @Before
    public void setUp() {
        initMocks(this);
        carpoolService = new CarpoolService(carpoolDao, buddyDao, routeDao, carpoolBuddyDao);
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
        when(routeDao.getCarpoolNameListByLocation("Diamond District")).thenReturn(Arrays.asList("carpool-1"));
        when(carpoolDao.get("carpool-1")).thenReturn(expectedCarpool);
        List<Carpool> carpools = carpoolService.findAllCarpoolsByLocation("Diamond District");
        assertThat(carpools, hasItems(expectedCarpool));
    }

    @Test
    public void shouldNotFindCarpoolsByInvalidLocation() {
        when(routeDao.getCarpoolNameListByLocation("Invalid Location")).thenReturn(new ArrayList<String>());
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
    public void shouldInsertIntoRoutePlanWhileCreatingCarpool() throws Exception {
        Carpool carpool = new Carpool("name");
        ArrayList<String> routePoints = new ArrayList<String>(){{
            add("Domlur");
            add("MG Road");
        }};
        carpool.setRoutePoints(routePoints);
        carpoolService.insert(carpool);
        verify(routeDao).insert("name", routePoints.get(0), 1);
    }

    @Test
    public void shouldInsertIntoCarpoolBuddy() throws Exception {
        Carpool carpool = new Carpool("name");
        carpool.setCarpoolBuddies(new ArrayList<CarpoolBuddy>(){
            {
                add(new CarpoolBuddy(new Buddy("name"),"pickupLocation",new LocalTime(10,0)));
            }
        });
        carpoolService.insert(carpool);
        verify(carpoolBuddyDao).insert(carpool.getCarpoolBuddies().get(0), carpool);
    }

    @Test
    public void shouldReturnBuddyListInCarpool() throws Exception {
        Carpool carpool = new Carpool("name");
        when(carpoolDao.get("name")).thenReturn(carpool);
        carpoolService.getByName("name");
        verify(carpoolBuddyDao).getCarpoolBuddiesByCarpoolName(carpool.getName());
    }

    @Test
    public void shouldReturnRouteListInCarpool(){
        Carpool carpool = new Carpool("carpool-test");
        when(carpoolDao.get("carpool-test")).thenReturn(carpool);
        carpoolService.getByName("carpool-test");
        verify(routeDao).getLocationsFor("carpool-test");
    }

    @Test
    public void shouldReturnTrueIfLoggedUserIsInTheCarpool() throws Exception {
        Carpool carpool = new Carpool("name");
        ArrayList<CarpoolBuddy> carpoolBuddies = new ArrayList<CarpoolBuddy>();
        carpoolBuddies.add(new CarpoolBuddy(new Buddy("username", "name", "123", "name@domain.com", "home"), "testPoint", Constants.TIME_FORMATTER.parseLocalTime("10:00")));
        carpool.setCarpoolBuddies(carpoolBuddies);
        Assert.assertTrue(carpoolService.hasBuddy("username", carpool));
    }

    @Test
    public void shouldReturnFalseIfLoggedUserIsNotInTheCarpool() throws Exception {
        Carpool carpool = new Carpool("name");
        ArrayList<CarpoolBuddy> carpoolBuddies = new ArrayList<CarpoolBuddy>();
        carpoolBuddies.add(new CarpoolBuddy(new Buddy("username", "name", "123", "name@domain.com", "home"), "testPoint", Constants.TIME_FORMATTER.parseLocalTime("10:00")));
        carpool.setCarpoolBuddies(carpoolBuddies);
        Assert.assertFalse(carpoolService.hasBuddy("otherusername", carpool));
    }

    @Test
    public void testFullCarpoolJoinCheckShouldReturnFalse() throws Exception {
        Carpool carpool = new Carpool("name");
        carpool.setCapacity(1);
        ArrayList<Buddy> buddies = new ArrayList<Buddy>();
        buddies.add(new Buddy("username", "name", "123", "name@domain.com", "home"));
        carpool.setBuddies(buddies);
        Assert.assertFalse(carpoolService.canUserSendRequest("someoneelse", carpool));
    }
    @Test
    public void testJoinCheckWhenBuddyAlreadyInCarpoolShouldReturnFalse() throws Exception {
        Carpool carpool = new Carpool("name");
        carpool.setCapacity(3);
        ArrayList<Buddy> buddies = new ArrayList<Buddy>();
        buddies.add(new Buddy("username", "name", "123", "name@domain.com", "home"));
        carpool.setBuddies(buddies);
        Assert.assertFalse(carpoolService.canUserSendRequest("username", carpool));
    }
}
