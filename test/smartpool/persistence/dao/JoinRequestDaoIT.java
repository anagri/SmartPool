package smartpool.persistence.dao;

import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import smartpool.domain.Buddy;
import smartpool.domain.JoinRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class JoinRequestDaoIT {

    private JoinRequestDao joinRequestDao;
    private String buddyUsername;
    private String carpoolName;

    @Before
    public void setUp() throws Exception {
        joinRequestDao = new JoinRequestDao();
        buddyUsername = "test.twu";
        carpoolName = "carpool-1";
    }

    @Test
    public void shouldInsertRequestToDB() {
        joinRequestDao.sendJoinRequest(new JoinRequest(buddyUsername, carpoolName, "diamond district", "Domlur", new LocalTime(9, 0)));

        JoinRequest returnedRequest = joinRequestDao.selectUsersRequest(buddyUsername, carpoolName);
        assertThat(returnedRequest, not(nullValue()));
    }

    @Test
    public void shouldVerifyRequestSentByABuddy() {

        Buddy buddy = new Buddy(buddyUsername);

        assertFalse(joinRequestDao.isRequestSent(buddy, carpoolName));
        joinRequestDao.sendJoinRequest(new JoinRequest(buddyUsername, carpoolName, "diamond district", "Domlur", new LocalTime(9, 0)));
        assertTrue(joinRequestDao.isRequestSent(buddy, carpoolName));
    }

    @Test
    public void testShouldInsertTimeInProperFormat() throws Exception {
        JoinRequest joinRequest = new JoinRequest(buddyUsername, carpoolName, "diamond district", "Here", new LocalTime(8, 30));

        joinRequestDao.sendJoinRequest(joinRequest);

        JoinRequest returnedRequest = joinRequestDao.selectUsersRequest(buddyUsername, carpoolName);
        assertThat(returnedRequest, is(not(nullValue())));
    }

    @Test
    public void testShouldDeleteUsersJoinRequest() throws Exception {
        joinRequestDao.sendJoinRequest(new JoinRequest(buddyUsername, carpoolName, "diamond district", "Domlur", new LocalTime(9, 0)));
        joinRequestDao.deleteUsersRequest(buddyUsername, carpoolName);
        assertThat(joinRequestDao.selectUsersRequest(buddyUsername, carpoolName), is(nullValue()));
    }

    @After
    public void tearDown() throws Exception {
        joinRequestDao.deleteUsersRequest(buddyUsername, carpoolName);
    }
}
