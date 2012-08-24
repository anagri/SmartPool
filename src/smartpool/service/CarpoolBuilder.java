package smartpool.service;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import smartpool.domain.BuddyProfile;
import smartpool.domain.CabType;
import smartpool.domain.Carpool;
import smartpool.domain.Status;

import java.util.ArrayList;

public class CarpoolBuilder {
    private static final ArrayList<BuddyProfile> buddyProfiles = new ArrayList<BuddyProfile>();
    static {
        buddyProfiles.add(BuddyProfileBuilder.buddyProfile_1);
        buddyProfiles.get(0).setPickupPoint("Diamond District");
        buddyProfiles.get(0).setPickupTime("0830");
        buddyProfiles.add(BuddyProfileBuilder.buddyProfile_2);
        buddyProfiles.get(1).setPickupPoint("Mars");
        buddyProfiles.get(1).setPickupTime("0930");
    }
    public static Carpool CARPOOL_1 = new Carpool("carpool-1", LocalDate.now(), "Diamond District", CabType.COMPANY, 100, LocalTime.now(), Status.RUNNING, buddyProfiles);
}
