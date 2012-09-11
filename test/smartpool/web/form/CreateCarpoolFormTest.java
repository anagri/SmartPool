package smartpool.web.form;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import smartpool.common.Constants;
import smartpool.domain.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateCarpoolFormTest {

    ArrayList<CarpoolBuddy> carpoolBuddies = new ArrayList<CarpoolBuddy>();
    Buddy buddy;
    private String carpoolFrom;
    private String carpoolTo;
    private String proposedStartDate;

    @Before
    public void setUp() throws Exception {
        buddy = new Buddy("buddyName");
        carpoolBuddies.add(new CarpoolBuddy(buddy, "PickupPoint", new LocalTime(10, 0)));
        carpoolFrom = "from";
        carpoolTo = "to";
        proposedStartDate = "16/9/2012";
    }

    @Test
    public void shouldCreateAValidDomainObjectForCarpool() throws Exception {
        CreateCarpoolForm createCarpoolForm = new CreateCarpoolForm(carpoolFrom, carpoolTo, proposedStartDate,"PickupPoint","10:00","PERSONAL", "6", "11:00","18:00","route,points");

        ArrayList<String> routePoints = new ArrayList<String>();
        routePoints.add("route");
        routePoints.add("points");

        assertThat(createCarpoolForm.getDomainObject(buddy), equalTo(new Carpool("from - to", Constants.DATE_FORMATTER.parseLocalDate(proposedStartDate), CabType.PERSONAL, 0, Constants.TIME_FORMATTER.parseLocalTime("11:00"), Constants.TIME_FORMATTER.parseLocalTime("18:00"), Status.NOT_STARTED, carpoolBuddies, 6, routePoints)));
    }

    @Test
    public void shouldNotHaveBlankRoutePointInDomainObject() throws Exception {
        CreateCarpoolForm createCarpoolForm = new CreateCarpoolForm(carpoolFrom, carpoolTo, proposedStartDate,"PickupPoint","10:00","PERSONAL", "6", "11:00","18:00","route,,points");
        assertThat(createCarpoolForm.getDomainObject(buddy).getRoutePoints().contains(""),equalTo(false));
    }
}
