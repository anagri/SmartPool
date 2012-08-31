package smartpool.persistence.dao;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import smartpool.domain.Buddy;

import java.util.List;

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
        Assert.assertEquals(expectedBuddy, buddyDao.selectAllBuddies().get(0));
    }

    @Test
    public void shouldGetBuddyListByCarpoolName() {
        List<Buddy> buddyList = buddyDao.getBuddyListByCarpoolName("carpool-1");
        Assert.assertEquals(1, buddyList.size());
        Assert.assertEquals("Diamond District", buddyList.get(0).getPickupPoint());
    }
}
