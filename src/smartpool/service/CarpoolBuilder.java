package smartpool.service;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import smartpool.domain.Buddy;
import smartpool.domain.CabType;
import smartpool.domain.Carpool;
import smartpool.domain.Status;

import java.util.ArrayList;

public class CarpoolBuilder {
    private static final ArrayList<Buddy> BUDDIES_CARPOOL_1 = new ArrayList<Buddy>();
    static {
        BUDDIES_CARPOOL_1.add(BuddyBuilder.buddy_1);
        BUDDIES_CARPOOL_1.get(0).setPickupPoint("Diamond District");
        BUDDIES_CARPOOL_1.get(0).setPickupTime(new LocalTime(8, 30));
        BUDDIES_CARPOOL_1.add(BuddyBuilder.buddy_2);
        BUDDIES_CARPOOL_1.get(1).setPickupPoint("Mars");
        BUDDIES_CARPOOL_1.get(1).setPickupTime(new LocalTime(9, 30));
    }
    private static final ArrayList<Buddy> BUDDIES_CARPOOL_2 = new ArrayList<Buddy>();
    static {
        BUDDIES_CARPOOL_2.add(BuddyBuilder.buddy_3);
        BUDDIES_CARPOOL_2.get(0).setPickupPoint("Chinchpokhli");
        BUDDIES_CARPOOL_2.get(0).setPickupTime(new LocalTime(8, 30));
        BUDDIES_CARPOOL_2.add(BuddyBuilder.buddy_4);
        BUDDIES_CARPOOL_2.get(1).setPickupPoint("Pluto");
        BUDDIES_CARPOOL_2.get(1).setPickupTime(new LocalTime(10, 30));
    }

    private static final ArrayList<String> ROUTE_PLAN_1 = new ArrayList<String>();
    static {
        ROUTE_PLAN_1.add("Domlur");
        ROUTE_PLAN_1.add("Kormangala");
    }
    public static Carpool CARPOOL_1 = new Carpool("carpool-1", LocalDate.now(), "Diamond District", CabType.COMPANY, 100, new LocalTime(9, 0), LocalTime.now(), Status.RUNNING, BUDDIES_CARPOOL_1, 4, ROUTE_PLAN_1);
}
