package smartpool.persistence.dao;

import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import smartpool.domain.Buddy;
import smartpool.domain.JoinRequest;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class JoinRequestDaoIT {

    private JoinRequestDao joinRequestDao;
    private String buddyUsername;
    private String carpoolName;
    private UUID uid;

    @Before
    public void setUp() throws Exception {
        joinRequestDao = new JoinRequestDao();
        buddyUsername = "mzhao";
        carpoolName = "carpool-1";
        uid = UUID.randomUUID();
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
        joinRequestDao.deletePendingRequest(uid.toString());
    }

    @Test
    public void shouldAddUniqueIdToRequest() throws Exception {
        joinRequestDao.addUniqueIdToPendingRequest(buddyUsername, carpoolName, uid);
        assertThat(joinRequestDao.getUniqueIdFromPendingRequest(buddyUsername, carpoolName),is(uid));
    }

    @Test
    public void shouldDeletePendingRequest() throws Exception {
        joinRequestDao.addUniqueIdToPendingRequest(buddyUsername, carpoolName, uid);
        joinRequestDao.deletePendingRequest(uid.toString());
        assertNull(joinRequestDao.getUniqueIdFromPendingRequest(buddyUsername, carpoolName));
    }

    @Test
    public void shouldGetBuddyUserNameFromUid() throws Exception {
        joinRequestDao.addUniqueIdToPendingRequest(buddyUsername, carpoolName, uid);
        joinRequestDao.getBuddyUserNameFromUid(uid.toString());
        assertThat(joinRequestDao.getBuddyUserNameFromUid(uid.toString()),equalTo(buddyUsername));
    }

    @Test
    public void shouldGetCarpoolNameFromUid() throws Exception {
        joinRequestDao.addUniqueIdToPendingRequest(buddyUsername, carpoolName, uid);
        joinRequestDao.getCarpoolNameFromUid(uid.toString());
        assertThat(joinRequestDao.getCarpoolNameFromUid(uid.toString()),equalTo(carpoolName));
    }

    @Test
    public void shouldReturnTrueForExistingUid() throws Exception {
        joinRequestDao.addUniqueIdToPendingRequest(buddyUsername, carpoolName, uid);
        assertTrue(joinRequestDao.isUidPresent(uid.toString()));
    }
}
