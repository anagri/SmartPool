package smartpool.persistence.dao;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.domain.CarpoolBuddy;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CarpoolBuddyDaoIT {

    private CarpoolBuddyDao carpoolBuddyDao;

    @Before
    public void setUp() throws Exception {
        carpoolBuddyDao = new CarpoolBuddyDao();
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
        CarpoolBuddy carpoolBuddy = new CarpoolBuddy(new Buddy("prithvin"),"location",new LocalTime(10,0));
        carpoolBuddyDao.insert(carpoolBuddy, new Carpool("carpool-1"));
        assertThat(carpoolBuddyDao.getCarpoolBuddiesByCarpoolName("carpool-1").contains(carpoolBuddy),equalTo(true));
        carpoolBuddyDao.remove(carpoolBuddy.getBuddy().getUserName(), "carpool-1");
    }   
}
