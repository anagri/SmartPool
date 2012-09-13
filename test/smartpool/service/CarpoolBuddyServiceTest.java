package smartpool.service;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.builder.CarpoolBuilder;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.domain.CarpoolBuddy;
import smartpool.persistence.dao.CarpoolBuddyDao;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class CarpoolBuddyServiceTest {
    @Mock
    private CarpoolBuddyDao carpoolBuddyDao;

    private CarpoolBuddyService carpoolBuddyService;
    private CarpoolBuddy carpoolBuddy;
    private Carpool carpool;
    private Buddy buddy;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        carpoolBuddyService = new CarpoolBuddyService(carpoolBuddyDao);
        buddy = new Buddy("username");
        carpoolBuddy = new CarpoolBuddy(buddy,"pickupPoint",new LocalTime());
        carpool = CarpoolBuilder.CARPOOL_1;
    }

    @Test
    public void shouldDeleteBuddyFromCarpool() throws Exception {
        String carpoolName = "cp - name";
        String buddyUserName = "buddy name";
        carpoolBuddyService.delete(carpoolName, buddyUserName);
        verify(carpoolBuddyDao).remove(buddyUserName,carpoolName);
    }

    @Test
    public void shouldInsertBuddyToCarpool() throws Exception {
        carpoolBuddyService.insert(carpoolBuddy,carpool);
        verify(carpoolBuddyDao).insert(carpoolBuddy,carpool);
    }

    @Test
    public void shouldGetCarpoolBuddyFromUsernameAndCarpoolName() throws Exception {
        when(carpoolBuddyDao.getCarpoolBuddyFromUsernameAndCarpoolName(buddy.getUserName(),carpool.getName())).thenReturn(carpoolBuddy);
        assertEquals(carpoolBuddy,carpoolBuddyService.getCarpoolBuddy(buddy.getUserName(),carpool.getName()));
        verify(carpoolBuddyDao).getCarpoolBuddyFromUsernameAndCarpoolName(buddy.getUserName(),carpool.getName());
    }
}
