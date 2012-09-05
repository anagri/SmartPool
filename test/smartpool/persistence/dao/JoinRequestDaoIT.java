package smartpool.persistence.dao;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import smartpool.domain.Buddy;
import smartpool.domain.JoinRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.notNull;

public class JoinRequestDaoIT {

    private JoinRequestDao joinRequestDao;

    @Before
    public void setUp() throws Exception {
        joinRequestDao = new JoinRequestDao();
    }

    @Test
    @Ignore
    public void shouldInsertRequestToDB() {
        joinRequestDao.sendJoinRequest(new JoinRequest("ayusht", "carpool-1", "Domlur", new LocalTime(9, 0), "diamond district"));
    }

    @Test
    @Ignore
    public void shouldVerifyRequestSentByABuddy() {
        String buddyName = "mdaliej";
        String carpoolName = "carpool-1";
        Buddy ali = new Buddy(buddyName);

        assertFalse(joinRequestDao.isRequestSent(ali, carpoolName));

        joinRequestDao.sendJoinRequest(new JoinRequest(buddyName, carpoolName, "Domlur", new LocalTime(9, 0), "diamond district"));

        assertTrue(joinRequestDao.isRequestSent(ali, carpoolName));
    }

    @Ignore
    @Test
    public void testShouldInsertTimeInProperFormat() throws Exception {
        JoinRequest joinRequest = new JoinRequest("test.twu", "carpool-1", "Here", new LocalTime(8, 30), "diamond district");

        joinRequestDao.sendJoinRequest(joinRequest);

        JoinRequest returnedRequest = joinRequestDao.selectUsersRequest(new Buddy("test.twu"), "carpool-1");
        assertThat(returnedRequest, is(notNull()));
    }

}
