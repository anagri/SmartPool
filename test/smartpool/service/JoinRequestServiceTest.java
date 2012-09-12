package smartpool.service;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.builder.BuddyBuilder;
import smartpool.domain.Buddy;
import smartpool.domain.CarpoolBuddy;
import smartpool.domain.JoinRequest;
import smartpool.persistence.dao.CarpoolBuddyDao;
import smartpool.persistence.dao.JoinRequestDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertTrue;
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

    private Buddy buddy;
    private String carpoolName;

    @Before
    public void setUp() {
        initMocks(this);
        buddy = BuddyBuilder.buddy_1;
        carpoolName = "carpool-1";
        joinRequestService = new JoinRequestService(joinRequestDao, carpoolBuddyDao, mailService);
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
        carpoolName = "carpool-2";
        JoinRequest joinRequest = new JoinRequest("suganthk", carpoolName, "address", "pickupPoint", new LocalTime(10, 0));
        when(carpoolBuddyDao.getCarpoolBuddiesByCarpoolName(carpoolName)).thenReturn(new ArrayList<CarpoolBuddy>());
        ArrayList<String> buddyEmailList = joinRequestService.getCarpoolBuddies(carpoolName);

        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        String approveLink="approve link";
        String disApproveLink="disapprove link";

        String subject = "SmartPool New Buddy Join Request";
        String message = "Smartpool Notification Date  :  %s<br /><br />" +
                "<b>%s</b> has requested to join <b>%s</b><br /><br />"+
                "<u>Details</u><br /><br />"+
                "%s<br /><br />"+
                "Approve : Click on this link %s<br /><br />"+
                "Disapprove : Click on this link %s<br /><br />"+
                "----------------------------------------<br /><br />"+
                "*This is a system generated email. Please DO NOT reply.";
        message = String.format(message,date, buddy.getName(), joinRequest.getCarpoolName(),joinRequest,approveLink,disApproveLink);
        joinRequestService.sendEmailToList(joinRequest, buddy);
        verify(mailService).sendMailToList(buddyEmailList, subject, message);
    }

    @Test
    public void shouldAddUniqueId() throws Exception {
        JoinRequest joinRequest = new JoinRequest("suganthk", carpoolName, "address", "pickupPoint", new LocalTime(10, 0));
        joinRequestService.sendJoinRequest(joinRequest,new Buddy("mdaliej"));

        verify(joinRequestDao).addUniqueIdToPendingRequest(buddy.getUserName(), carpoolName, null);
    }
}
