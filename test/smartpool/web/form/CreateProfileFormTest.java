package smartpool.web.form;

import org.joda.time.LocalTime;
import org.junit.Test;
import smartpool.domain.Buddy;

import static org.junit.Assert.assertThat;
import static smartpool.util.matchers.ReflectionMatcher.reflectionEquals;

public class CreateProfileFormTest {
    @Test
    public void shouldCreateValidDomainObject() throws Exception {
        Buddy buddy = new CreateProfileForm("test.twu", "test carpool", "Gate 8", "08:30",
                "Diamond District, Gate 8", "+91 9999 999 999","test@thoughtworks.com").createBuddy();
        assertThat(buddy, reflectionEquals(new Buddy("test.twu", "test carpool","+91 9999 999 999",
                "test@thoughtworks.com", "Diamond District, Gate 8", "Gate 8", new LocalTime(8, 30))));
    }

    @Test
    public void shouldCreateValidDomainObjectIfPreferredTimeHasNoZeroPadding() throws Exception {
        Buddy buddy = new CreateProfileForm("test.twu", "test carpool", "Gate 8", "8:30",
                "Diamond District, Gate 8", "+91 9999 999 999","test@thoughtworks.com").createBuddy();
        assertThat(buddy, reflectionEquals(new Buddy("test.twu", "test carpool","+91 9999 999 999",
                "test@thoughtworks.com", "Diamond District, Gate 8", "Gate 8", new LocalTime(8, 30))));
    }
}
