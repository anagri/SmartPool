package smartpool.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class MailTest {

    @Mock
    private Message message;
    private Mail mail;

    @Before
    public void setUp() {
        initMocks(this);
        mail = new Mail(message);
    }

    @Test
    public void shouldThrowAnExceptionIfInvalidRecipient() {
        try {
            mail.compose("invalid", "", "");
            fail("Should have thrown an exception");
        } catch (MessagingException e) {
            assertEquals("Missing final '@domain'", e.getMessage());
        }
    }

    @Test
    public void shouldComposeAMessage() throws MessagingException {
        mail.compose("valid@abc.com", "subject", "body");

        Mockito.verify(message).setFrom(new InternetAddress("SmartPool"));
        Mockito.verify(message).setRecipient(Message.RecipientType.TO, new InternetAddress("valid@abc.com"));
        Mockito.verify(message).setSubject("subject");
        Mockito.verify(message).setText("body");
    }
}
