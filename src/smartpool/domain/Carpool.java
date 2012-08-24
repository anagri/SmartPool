package smartpool.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class Carpool {

    public final String name;
    public final LocalDate startDate;
    public final String pickupPoint;
    public final CabType cabType;
    public final int totalCharge;
    public final LocalTime officePickupTime;
    public final Status status;

    public Carpool(String name, LocalDate startDate, String pickupPoint, CabType cabType, int totalCharge, LocalTime officePickupTime, Status status) {
        this.name = name;
        this.startDate = startDate;
        this.pickupPoint = pickupPoint;
        this.cabType = cabType;
        this.totalCharge = totalCharge;
        this.officePickupTime = officePickupTime;
        this.status = status;
    }
}
