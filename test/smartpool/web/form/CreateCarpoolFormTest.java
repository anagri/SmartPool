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
    private String pickupPoint;
    private String pickupTime;
    private String cabType;
    private String capacity;
    private String officeArrivalTime;
    private String officeDepartureTime;
    private String badRoute;
    private String goodRoute;

    @Before
    public void setUp() throws Exception {
        buddy = new Buddy("buddyName");
        carpoolFrom = "from";
        carpoolTo = "to";
        proposedStartDate = "16/9/2012";
        pickupPoint = "PickupPoint";
        pickupTime = "10:00";
        cabType = "PERSONAL";
        capacity = "6";
        officeArrivalTime = "11:00";
        officeDepartureTime = "18:00";
        badRoute = "route,,points";
        goodRoute = "route,points";
        carpoolBuddies.add(new CarpoolBuddy(buddy, pickupPoint, new LocalTime(10, 0)));
    }

    @Test
    public void shouldCreateAValidDomainObjectForCarpool() throws Exception {
        CreateCarpoolForm createCarpoolForm = new CreateCarpoolForm(carpoolFrom, carpoolTo, proposedStartDate, pickupPoint, pickupTime, cabType, capacity, officeArrivalTime, officeDepartureTime, goodRoute);

        ArrayList<String> routePoints = new ArrayList<String>();
        routePoints.add("route");
        routePoints.add("points");

        assertThat(createCarpoolForm.getDomainObject(buddy), equalTo(new Carpool("from - to", Constants.DATE_FORMATTER.parseLocalDate(proposedStartDate), CabType.PERSONAL, 0, Constants.TIME_FORMATTER.parseLocalTime(officeArrivalTime), Constants.TIME_FORMATTER.parseLocalTime(officeDepartureTime), Status.NOT_STARTED, carpoolBuddies, 6, routePoints)));
    }

    @Test
    public void shouldNotHaveBlankRoutePointInDomainObject() throws Exception {
        CreateCarpoolForm createCarpoolForm = new CreateCarpoolForm(carpoolFrom, carpoolTo, proposedStartDate, pickupPoint, pickupTime, cabType, capacity, officeArrivalTime, officeDepartureTime, badRoute);
        assertThat(createCarpoolForm.getDomainObject(buddy).getRoutePoints().contains(""), equalTo(false));
    }
}
