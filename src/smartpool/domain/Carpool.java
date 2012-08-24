package smartpool.domain;

import org.joda.time.LocalDate;

public class Carpool {

    private String name;
    private LocalDate startDate;
    private String pickupPoint;
    final public CabType cabType;

    public Carpool(String name, LocalDate startDate, String pickupPoint, CabType cabType) {
        this.name = name;
        this.startDate = startDate;
        this.pickupPoint =pickupPoint;
        this.cabType = cabType;
    }

    public String name() {
        return name;
    }

    public LocalDate startDate() {
        return startDate;
    }
}
