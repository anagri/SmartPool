package smartpool.service;

import junit.framework.Assert;
import org.junit.Test;
import smartpool.domain.Buddy;

public class BuddyServiceTest {

    @Test
    public void shouldGetBuddyFromBuddyDao() throws Exception {
        Buddy expectedBuddy = new Buddy("prithvin");
        BuddyService buddyService = new BuddyService();
        Assert.assertEquals(expectedBuddy, buddyService.getBuddy("prithvin"));
    }
}
