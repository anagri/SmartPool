package smartpool.domain;

import org.joda.time.LocalDate;

public class Carpool {

    private String name;
    private LocalDate startDate;
    private String pickupPoint;

    public Carpool(String name, LocalDate startDate, String pickupPoint) {
        this.name = name;
        this.startDate = startDate;
        this.pickupPoint =pickupPoint;
    }

    public String name() {
        return name;
    }

    public LocalDate startDate() {
        return startDate;
    }
}
