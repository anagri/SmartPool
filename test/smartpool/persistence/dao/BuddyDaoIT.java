package smartpool.persistence.dao;

import junit.framework.Assert;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BuddyDaoIT {

    private BuddyDao buddyDao;

    @Before
    public void setUp() throws Exception {
        buddyDao = new BuddyDao();
    }

    @Test
    public void shouldGetBuddyByUserName(){
        Buddy expectedBuddy = new Buddy("arnavku");
        Assert.assertEquals(expectedBuddy, buddyDao.selectBuddy("arnavku"));
        Assert.assertEquals("Diamond District", buddyDao.selectBuddy("arnavku").getPickupPoint());
    }

    @Test
    public void shouldGetFirstBuddy(){
        Buddy expectedBuddy = new Buddy("arnavku");
        assertTrue(buddyDao.selectAllBuddies().contains(expectedBuddy));
    }

    @Test
    public void shouldGetBuddyListByCarpoolName() {
        List<Buddy> buddyList = buddyDao.getBuddyListByCarpoolName("carpool-1");
        assertThat(buddyList.size(), not(0));
        Assert.assertEquals(new LocalTime(10, 50).toString("h:mm"), buddyList.get(0).getPickupTime().toString("h:mm"));
    }

    @Test
    @Ignore
    public void shouldAddBuddyToACarpool() throws Exception {
        Buddy buddy = new Buddy("vfranca");
        Carpool carpool = new Carpool("carpool-1");
        buddyDao.addToCarpool(buddy, carpool);
        assertThat(buddyDao.getBuddyListByCarpoolName("carpool-1").contains(buddy),equalTo(true));
    }
}
