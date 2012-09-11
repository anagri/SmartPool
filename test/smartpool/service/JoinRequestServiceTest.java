package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.builder.BuddyBuilder;
import smartpool.domain.Buddy;
import smartpool.persistence.dao.JoinRequestDao;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JoinRequestServiceTest {

    private JoinRequestService joinRequestService;
    @Mock
    private JoinRequestDao joinRequestDao;
    private Buddy buddy;
    private String carpoolName;

    @Before
    public void setUp() {
        initMocks(this);
        buddy = BuddyBuilder.buddy_1;
        carpoolName = "carpool-1";
        joinRequestService = new JoinRequestService(joinRequestDao);
    }

    @Test
    public void buddyRequestAlreadySentToJoinACarpool() {
        when(joinRequestDao.isRequestSent(buddy, carpoolName)).thenReturn(true);

        boolean actualResult = joinRequestService.isRequestSent(buddy, carpoolName);
        assertTrue(actualResult);
    }
}
