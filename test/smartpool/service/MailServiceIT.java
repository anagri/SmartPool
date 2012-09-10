package smartpool.service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
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
    public void shouldSendEmailsToList() throws Exception {
        ArrayList<String> emailList = new ArrayList<String>();
        emailList.add("prithvin@thoughtworks.com");
        emailList.add("ishak@thoughtworks.com");

        assertTrue(mailService.sendMailToList(emailList, "", ""));
    }

    @Test
    public void shouldFailWhileSendingEmailToWrongAddress() throws Exception {
        ArrayList<String> emailList = new ArrayList<String>();
        emailList.add("prithvin@thoughtworks.com");
        emailList.add("jjajajaj");
        emailList.add("ishak@thoughtworks.com");

        assertFalse(mailService.sendMailToList(emailList, "", ""));
    }
}
