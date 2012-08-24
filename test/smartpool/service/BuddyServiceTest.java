package smartpool.service;

import junit.framework.Assert;
import org.junit.Test;

public class BuddyServiceTest {

    @Test
    public void shouldGetBuddyFromBuddyDao() throws Exception {

         String expectedUserName="1";
        BuddyService buddyService=new BuddyService();
        Assert.assertEquals(expectedUserName,buddyService.getUserName());
    }
}
