package smartpool.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import smartpool.domain.Mail;

import javax.mail.Message;
import javax.mail.Transport;
import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Transport.class)
public class MailServiceTest {

    MailService mailService;
    @Mock
    Mail mail;
    @Mock
    Message message;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        mailService = new MailService(mail);
    }

    @Test
    public void shouldSendEmailsToList() throws Exception {
        when(mail.compose("suganthk@thoughtworks.com", "Feedback", "You are dumb!")).thenReturn(message);
        PowerMockito.mockStatic(Transport.class);

        mailService.sendMailToList(Arrays.asList("suganthk@thoughtworks.com"), "Feedback", "You are dumb!");

        PowerMockito.verifyStatic(times(1));
        Transport.send(message);
    }
}
