package smartpool.service;

import org.joda.time.LocalDate;
import smartpool.domain.CabType;
import smartpool.domain.Carpool;

public class CarpoolBuilder {
    public static Carpool CARPOOL_1 = new Carpool("carpool-1", LocalDate.now(), "Diamond District", CabType.COMPANY);
}
