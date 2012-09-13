package smartpool.persistence.dao;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import smartpool.builder.CarpoolBuilder;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.domain.CarpoolBuddy;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CarpoolBuddyDaoIT {

    private CarpoolBuddyDao carpoolBuddyDao;
    private CarpoolBuddy carpoolBuddy;
    private Carpool carpool;
    private Buddy buddy;

    @Before
    public void setUp() throws Exception {
        carpoolBuddyDao = new CarpoolBuddyDao();
        buddy = new Buddy("prithvin");
        carpoolBuddy = new CarpoolBuddy(buddy,"location",new LocalTime(10,0));
        carpool = CarpoolBuilder.CARPOOL_1;
    }

    @Test
    public void shouldGetCarpoolBuddyFromDB() throws Exception {
        ArrayList<CarpoolBuddy> carpoolBuddies = carpoolBuddyDao.getCarpoolBuddiesByCarpoolName("carpool-1");
        assertThat(carpoolBuddies.size(),not(0));
    }

    @Test
    public void shouldGetBuddyInsideCarpoolBuddyFromDB(){
        ArrayList<CarpoolBuddy> carpoolBuddies = carpoolBuddyDao.getCarpoolBuddiesByCarpoolName("carpool-1");
        assertThat(carpoolBuddies.get(0).getBuddy(), notNullValue());
    }

    @Test
    public void shouldInsertIntoDB() throws Exception {
        carpoolBuddyDao.insert(carpoolBuddy, carpool);
        assertThat(carpoolBuddyDao.getCarpoolBuddiesByCarpoolName(carpool.getName()).contains(carpoolBuddy), equalTo(true));
        carpoolBuddyDao.remove(carpoolBuddy.getBuddy().getUserName(), carpool.getName());
    }

    @Test
    public void shouldGetCarpoolBuddyFormUserNameAndCarpoolName() throws Exception {
        carpoolBuddyDao.insert(carpoolBuddy, carpool);
        CarpoolBuddy carpoolBuddyActual = carpoolBuddyDao.getCarpoolBuddyFromUsernameAndCarpoolName(buddy.getUserName(),carpool.getName());
        assertEquals(carpoolBuddy,carpoolBuddyActual);
        carpoolBuddyDao.remove(buddy.getUserName(), carpool.getName());
    }
}
