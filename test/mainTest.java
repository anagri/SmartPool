import smartpool.common.Mail;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class mainTest {
    @org.junit.Test
    public void shouldTestMail() throws Exception {
        assertThat(new Mail().sendMail("prithvin@thoughtworks.com", "Subject", "Message Body"),is(true));
    }
}
