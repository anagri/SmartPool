package smartpool.web.form;

import org.joda.time.LocalTime;
import org.junit.Test;
import smartpool.common.Constants;
import smartpool.domain.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateCarpoolFormTest {
    @Test
    public void shouldCreateAValidDomainObjectForCarpool() throws Exception {
        CreateCarpoolForm createCarpoolForm = new CreateCarpoolForm("from","to","16/9/2012","PickupPoint","10:00","PERSONAL", 6, "11:00","18:00","route,points");

        ArrayList<CarpoolBuddy> carpoolBuddies = new ArrayList<CarpoolBuddy>();
        Buddy buddy = new Buddy("buddyName");
        carpoolBuddies.add(new CarpoolBuddy(buddy, "PickupPoint", new LocalTime(10, 0)));

        ArrayList<String> routePoints = new ArrayList<String>();
        routePoints.add("route");
        routePoints.add("points");

        assertThat(createCarpoolForm.getDomainObject(buddy), equalTo(new Carpool("from - to", Constants.DATE_FORMATTER.parseLocalDate("16/9/2012"), CabType.PERSONAL, 0, Constants.TIME_FORMATTER.parseLocalTime("11:00"), Constants.TIME_FORMATTER.parseLocalTime("18:00"), Status.NOT_STARTED, carpoolBuddies, 6, routePoints)));
    }

}
