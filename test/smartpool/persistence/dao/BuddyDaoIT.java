package smartpool.persistence.dao;

import junit.framework.Assert;
import org.junit.Test;
import smartpool.domain.Buddy;

public class BuddyDaoIT {
    @Test
    public void shouldGetBuddyByUserName(){
        Buddy expectedBuddy = new Buddy("prithvin");
        BuddyDao buddyDao = new BuddyDao();
        Assert.assertEquals(expectedBuddy, buddyDao.selectBuddy("prithvin"));
    }

    @Test
    public void shouldGetFirstBuddy(){
        Buddy expectedBuddy = new Buddy("arnavku");
        BuddyDao buddyDao = new BuddyDao();
        Assert.assertEquals(expectedBuddy, buddyDao.selectAllBuddies());
    }
}
