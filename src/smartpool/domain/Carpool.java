package smartpool.domain;

import org.joda.time.LocalDate;

public class Carpool {

    private String name;
    private LocalDate startDate;

    public Carpool(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public String name() {
        return name;
    }

    public LocalDate startDate() {
        return startDate;
    }
}
