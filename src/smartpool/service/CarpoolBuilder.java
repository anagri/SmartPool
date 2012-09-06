package smartpool.service;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import smartpool.domain.*;

import java.util.ArrayList;

public class CarpoolBuilder {
    private static final ArrayList<CarpoolBuddy> BUDDIES_CARPOOL_1 = new ArrayList<CarpoolBuddy>();
    static {
        BUDDIES_CARPOOL_1.add(new CarpoolBuddy(BuddyBuilder.buddy_1, "Diamond District", new LocalTime(8, 30)));
        BUDDIES_CARPOOL_1.add(new CarpoolBuddy(BuddyBuilder.buddy_2, "Mars", new LocalTime(9,30)));
    }
    private static final ArrayList<CarpoolBuddy> BUDDIES_CARPOOL_2 = new ArrayList<CarpoolBuddy>();
    static {
        BUDDIES_CARPOOL_1.add(new CarpoolBuddy(BuddyBuilder.buddy_3, "Chinchpokhli", new LocalTime(8, 30)));
        BUDDIES_CARPOOL_1.add(new CarpoolBuddy(BuddyBuilder.buddy_4, "Pluto", new LocalTime(10,30)));
    }

    private static final ArrayList<String> ROUTE_PLAN_1 = new ArrayList<String>();
    static {
        ROUTE_PLAN_1.add("Domlur");
        ROUTE_PLAN_1.add("Kormangala");
    }
    public static Carpool CARPOOL_1 = new Carpool("carpool-1", LocalDate.now(), CabType.COMPANY, 100, new LocalTime(9, 0), LocalTime.now(), Status.ACTIVE, BUDDIES_CARPOOL_1, 4, ROUTE_PLAN_1);
    public static final Carpool CARPOOL_2 = new Carpool("Domlur-1", LocalDate.now(), CabType.COMPANY, 100, new LocalTime(10, 0), LocalTime.now(), Status.PENDING, BUDDIES_CARPOOL_2, 4, new ArrayList<String>());
}
