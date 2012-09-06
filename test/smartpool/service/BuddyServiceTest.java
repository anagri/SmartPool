package smartpool.service;

import junit.framework.Assert;
import org.junit.Test;
import smartpool.domain.Buddy;
import smartpool.persistence.dao.BuddyDao;

public class BuddyServiceTest {

    @Test
    public void shouldGetBuddyFromBuddyDao() throws Exception {
        Buddy expectedBuddy = new Buddy("prithvin");
        BuddyService buddyService = new BuddyService(new BuddyDao());
        Assert.assertEquals(expectedBuddy, buddyService.getBuddy("prithvin"));
    }
}
