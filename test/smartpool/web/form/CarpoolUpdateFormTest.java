package smartpool.web.form;

import org.junit.Test;
import smartpool.domain.Carpool;
import smartpool.domain.Status;

import static org.junit.Assert.assertThat;
import static smartpool.util.matchers.ReflectionMatcher.reflectionEquals;

public class CarpoolUpdateFormTest {

    @Test
    public void shouldUpdateCarpool() throws Exception {
        Carpool original = new Carpool("name");
        original.setCapacity(3);
        original.setStatus(Status.NOT_STARTED);

        CarpoolUpdateForm carpoolUpdateForm = new CarpoolUpdateForm("ACTIVE", "3000", "6");

        Carpool expected = new Carpool("name");
        expected.setStatus(Status.ACTIVE);
        expected.setTotalCabCharges(3000);
        expected.setCapacity(6);

        assertThat(carpoolUpdateForm.createDomainObject(original), reflectionEquals(expected));


    }
}
