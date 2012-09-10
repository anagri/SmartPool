package smartpool.service;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.domain.Buddy;
import smartpool.persistence.dao.BuddyDao;
import smartpool.web.form.CreateProfileForm;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class BuddyServiceTest {

    @Mock
    BuddyDao buddyDao;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldGetBuddyFromBuddyDao() throws Exception {
        Buddy expectedBuddy = new Buddy("prithvin");
        BuddyService buddyService = new BuddyService(new BuddyDao());
        Assert.assertEquals(expectedBuddy, buddyService.getBuddy("prithvin"));
    }

    @Test
    public void shouldInsertIntoDatabase() {
        BuddyService buddyService = new BuddyService(buddyDao);
        Buddy buddy = new CreateProfileForm("new.user", "test", "address", "email", null, null, "09:00").createBuddy();
        buddyService.insert(buddy);
        verify(buddyDao).insertIntoBuddies(buddy);
    }

}
