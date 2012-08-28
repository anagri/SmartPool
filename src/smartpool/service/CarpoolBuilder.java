package smartpool.service;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import smartpool.domain.BuddyProfile;
import smartpool.domain.CabType;
import smartpool.domain.Carpool;
import smartpool.domain.Status;

import java.util.ArrayList;

public class CarpoolBuilder {
    private static final ArrayList<BuddyProfile> BUDDIES_CARPOOL_1 = new ArrayList<BuddyProfile>();
    static {
        BUDDIES_CARPOOL_1.add(BuddyProfileBuilder.buddyProfile_1);
        BUDDIES_CARPOOL_1.get(0).setPickupPoint("Diamond District");
        BUDDIES_CARPOOL_1.get(0).setPickupTime("0830");
        BUDDIES_CARPOOL_1.add(BuddyProfileBuilder.buddyProfile_2);
        BUDDIES_CARPOOL_1.get(1).setPickupPoint("Mars");
        BUDDIES_CARPOOL_1.get(1).setPickupTime("0930");
    }
    private static final ArrayList<BuddyProfile> BUDDIES_CARPOOL_2 = new ArrayList<BuddyProfile>();
    static {
        BUDDIES_CARPOOL_2.add(BuddyProfileBuilder.buddyProfile_3);
        BUDDIES_CARPOOL_2.get(0).setPickupPoint("Chinchpokhli");
        BUDDIES_CARPOOL_2.get(0).setPickupTime("0830");
        BUDDIES_CARPOOL_2.add(BuddyProfileBuilder.buddyProfile_4);
        BUDDIES_CARPOOL_2.get(1).setPickupPoint("Pluto");
        BUDDIES_CARPOOL_2.get(1).setPickupTime("0930");
    }

    private static final ArrayList<String> ROUTE_PLAN_1 = new ArrayList<String>();
    static {
        ROUTE_PLAN_1.add("Domlur");
        ROUTE_PLAN_1.add("Kormangala");
    }
    public static Carpool CARPOOL_1 = new Carpool("carpool-1", LocalDate.now(), "Diamond District", CabType.COMPANY, 100, new LocalTime(9, 0), LocalTime.now(), Status.RUNNING, BUDDIES_CARPOOL_1, 4, ROUTE_PLAN_1);
    public static final Carpool CARPOOL_2 = new Carpool("Domlur-1", LocalDate.now(), "Domlur", CabType.COMPANY, 100, new LocalTime(10, 0), LocalTime.now(), Status.PENDING, BUDDIES_CARPOOL_2, 4, new ArrayList<String>());
}
