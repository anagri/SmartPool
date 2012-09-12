package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.persistence.dao.CarpoolBuddyDao;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarpoolBuddyServiceTest {
    @Mock
    private CarpoolBuddyDao carpoolBuddyDao;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldDeleteBuddyFromCarpool() throws Exception {
        String carpoolName = "cp - name";
        String buddyUserName = "buddy name";
        new CarpoolBuddyService(carpoolBuddyDao).delete(carpoolName, buddyUserName);
        verify(carpoolBuddyDao).remove(buddyUserName,carpoolName);
    }
}
