package smartpool.web.form;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import smartpool.common.Constants;
import smartpool.domain.*;

import java.util.ArrayList;

public class CreateCarpoolForm {
    private String from;
    private String to;
    private LocalDate proposedStartDate;
    private String proposedStartDateStr;
    private String pickupPoint;
    private LocalTime pickupTime;
    private CabType cabType;
    private LocalTime officeArrivalTime;
    private LocalTime officeDepartureTime;
    private ArrayList<String> routePoints = new ArrayList<String>();

    public CreateCarpoolForm() {
    }

    public CreateCarpoolForm(String from, String to, String proposedStartDate, String pickupPoint, String pickupTime, String cabType, String officeArrivalTime, String officeDepartureTime, String routePoints) {

        this.from = from;
        this.to = to;
        this.proposedStartDate = Constants.DATE_FORMATTER.parseLocalDate(proposedStartDate);
        this.pickupPoint = pickupPoint;
        this.pickupTime = Constants.TIME_FORMATTER.parseLocalTime(pickupTime);
        this.cabType = CabType.valueOf(cabType);
        this.officeArrivalTime = Constants.TIME_FORMATTER.parseLocalTime(officeArrivalTime);
        this.officeDepartureTime = Constants.TIME_FORMATTER.parseLocalTime(officeDepartureTime);
        setRoutePoints(routePoints);
    }

    public Carpool getDomainObject(Buddy currentBuddy) {

        Carpool carpool = new Carpool(from + " - " + to, proposedStartDate, cabType, 0, officeArrivalTime, officeDepartureTime, Status.PENDING, null, 0, routePoints);
        ArrayList<CarpoolBuddy> carpoolBuddies = new ArrayList<CarpoolBuddy>();
        carpoolBuddies.add(new CarpoolBuddy(currentBuddy,pickupPoint,pickupTime));
        carpool.setCarpoolBuddies(carpoolBuddies);
        return carpool;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setProposedStartDate(String proposedStartDate) {
        this.proposedStartDate = Constants.DATE_FORMATTER.parseLocalDate(proposedStartDate);
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = Constants.TIME_FORMATTER.parseLocalTime(pickupTime);
    }

    public void setCabType(CabType cabType) {
        this.cabType = cabType;
    }

    public void setOfficeArrivalTime(String officeArrivalTime) {
        this.officeArrivalTime = Constants.TIME_FORMATTER.parseLocalTime(officeArrivalTime);
    }

    public void setOfficeDepartureTime(String officeDepartureTime) {
        this.officeDepartureTime = Constants.TIME_FORMATTER.parseLocalTime(officeDepartureTime);
    }

    public void setRoutePoints(String routePoints) {
        for (String routePoint : routePoints.split(",")) {
            this.routePoints.add(routePoint.trim());
        }
    }

    public void setProposedStartDateStr(String proposedStartDateStr) {
        this.proposedStartDateStr = proposedStartDateStr;
        this.proposedStartDate = Constants.DATE_FORMATTER.parseLocalDate(proposedStartDateStr);
    }
}
