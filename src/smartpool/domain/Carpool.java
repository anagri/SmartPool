package smartpool.domain;

import org.joda.time.LocalDate;

public class Carpool {

    public final String name;
    public final LocalDate startDate;
    public final String pickupPoint;
    public final CabType cabType;

    public Carpool(String name, LocalDate startDate, String pickupPoint, CabType cabType) {
        this.name = name;
        this.startDate = startDate;
        this.pickupPoint = pickupPoint;
        this.cabType = cabType;
    }
}
