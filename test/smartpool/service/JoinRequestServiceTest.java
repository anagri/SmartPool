package smartpool.service;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.builder.BuddyBuilder;
import smartpool.common.Constants;
import smartpool.domain.Buddy;
import smartpool.domain.CarpoolBuddy;
import smartpool.domain.JoinRequest;
import smartpool.persistence.dao.CarpoolBuddyDao;
import smartpool.persistence.dao.JoinRequestDao;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JoinRequestServiceTest {

    private JoinRequestService joinRequestService;
    @Mock
    private JoinRequestDao joinRequestDao;
    @Mock
    private CarpoolBuddyDao carpoolBuddyDao;
    @Mock
    private MailService mailService;
    @Mock
    private Properties appProperties;

    private Buddy buddy;
    private String carpoolName;
    private JoinRequest joinRequest;
    private UUID uuid;

    @Before
    public void setUp() {
        initMocks(this);
        buddy = BuddyBuilder.buddy_1;
        carpoolName = "carpool-1";

        joinRequestService = new JoinRequestService(joinRequestDao, carpoolBuddyDao, mailService,appProperties);

        when(appProperties.getProperty("hostName")).thenReturn("localhost");
        when(appProperties.getProperty("applicationName")).thenReturn("smartpool");
        when(appProperties.getProperty("port")).thenReturn("9090");
        uuid = UUID.randomUUID();
        when(joinRequestDao.getUniqueIdFromPendingRequest(buddy.getUserName(), carpoolName)).thenReturn(uuid);
        when(carpoolBuddyDao.getCarpoolBuddiesByCarpoolName(carpoolName)).thenReturn(new ArrayList<CarpoolBuddy>());


        joinRequest = new JoinRequest("suganthk", carpoolName, "address", "pickupPoint", new LocalTime(10, 0));
    }

    @Test
    public void buddyRequestAlreadySentToJoinACarpool() {
        when(joinRequestDao.isRequestSent(buddy, carpoolName)).thenReturn(true);

        boolean actualResult = joinRequestService.isRequestSent(buddy, carpoolName);
        assertTrue(actualResult);
    }


    @Test
    public void shouldGetEmailsOfBuddies() throws Exception {
        when(carpoolBuddyDao.getCarpoolBuddiesByCarpoolName("carpool-2")).thenReturn(new ArrayList<CarpoolBuddy>());
        joinRequestService.getCarpoolBuddies("carpool-2");
        verify(carpoolBuddyDao).getCarpoolBuddiesByCarpoolName("carpool-2");
    }

    @Test
    public void shouldSendEmailToList() throws Exception {
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        String smartPoolUrl = "http://"+appProperties.getProperty("hostName")+":"+appProperties.getProperty("port")+"/"+appProperties.getProperty("applicationName")+"/";
        String approveLink= smartPoolUrl+String.format(Constants.Approve_Link,joinRequestDao.getUniqueIdFromPendingRequest(buddy.getUserName(),carpoolName));
        String disApproveLink= smartPoolUrl+String.format(Constants.Disapprove_Link,joinRequestDao.getUniqueIdFromPendingRequest(buddy.getUserName(),joinRequest.getCarpoolName()));

        ArrayList<String> buddyEmailList = joinRequestService.getCarpoolBuddies(carpoolName);

        String subject = Constants.NEW_BUDDY_NOTIFICATION_SUBJECT;
        String message = String.format(Constants.NEW_BUDDY_NOTIFICATION_MESSAGE, date, buddy.getName(), joinRequest.getCarpoolName(),joinRequest,approveLink,disApproveLink);

        message = String.format(message,date, buddy.getName(), joinRequest.getCarpoolName(),joinRequest,approveLink,disApproveLink);
        joinRequestService.sendEmailToList(joinRequest, buddy);
        verify(mailService).sendMailToList(buddyEmailList, subject, message);
    }

    @Test
    public void shouldAddUniqueId() throws Exception {
        joinRequestService.sendJoinRequest(joinRequest, buddy, uuid);
        verify(joinRequestDao).addUniqueIdToPendingRequest(buddy.getUserName(), carpoolName, uuid);
    }

    @Test
    public void shouldDeletePendingRequest() throws Exception {
        joinRequestService.deletePendingRequest(uuid.toString());
        verify(joinRequestDao).deletePendingRequest(uuid.toString());
    }

    @Test
    public void shouldGetBuddyUserNameFromUid() throws Exception {
        String buddyUserName = "buddyUserName";
        when(joinRequestDao.getBuddyUserNameFromUid(uuid.toString())).thenReturn(buddyUserName);
        assertEquals(buddyUserName, joinRequestService.getBuddyUserNameFromUid(uuid.toString()));
        verify(joinRequestDao).getBuddyUserNameFromUid(uuid.toString());
    }

    @Test
    public void shouldGetCarpoolNameFromUid() throws Exception {
        when(joinRequestDao.getCarpoolNameFromUid(uuid.toString())).thenReturn(carpoolName);
        assertEquals(carpoolName,joinRequestService.getCarpoolNameFromUid(uuid.toString()));
        verify(joinRequestDao).getCarpoolNameFromUid(uuid.toString());
    }

    @Test
    public void shouldGetJoinRequestByUserNameAndCarpoolName() throws Exception {
        String userName = "userName";
        String carpoolName = "carpoolName";
        JoinRequest joinRequest = new JoinRequest();
        when(joinRequestDao.selectUsersRequest(userName,carpoolName)).thenReturn(joinRequest);
        assertThat(joinRequestService.getJoinRequestByUserNameAndCarpoolName(userName,carpoolName),is(joinRequest));
        verify(joinRequestDao).selectUsersRequest(userName,carpoolName);
    }
}
