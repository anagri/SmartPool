package smartpool.service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class MailServiceIT {


    MailService mailService;
    JoinRequestService joinRequestService;

    @Before
    public void setUp() throws Exception {
        mailService = new MailService();
        joinRequestService = new JoinRequestService();
    }

    @Test
    public void shouldSendEmailsToCarpoolBuddies() throws Exception {
        ArrayList<String> buddyEmailList = joinRequestService.getCarpoolBuddies("carpool-2");
        assertTrue(mailService.sendMailToList(buddyEmailList, "", ""));
    }
}
