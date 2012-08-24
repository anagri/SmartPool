package smartpool.service;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import smartpool.domain.CabType;
import smartpool.domain.Carpool;
import smartpool.domain.Status;

public class CarpoolBuilder {
    public static Carpool CARPOOL_1 = new Carpool("carpool-1", LocalDate.now(), "Diamond District", CabType.COMPANY, 100, LocalTime.now(), Status.RUNNING);
}
