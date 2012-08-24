package smartpool.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;

public class Carpool {
    final String name;
    final LocalDate startDate;
    final String pickupPoint;
    final CabType cabType;
    final int totalCharge;
    final LocalTime officePickupTime;
    final Status status;
    final ArrayList<BuddyProfile> buddyProfiles;

    public Carpool(String name, LocalDate startDate, String pickupPoint, CabType cabType, int totalCharge, LocalTime officePickupTime, Status status, ArrayList<BuddyProfile> buddyProfiles) {
        this.name = name;
        this.startDate = startDate;
        this.pickupPoint = pickupPoint;
        this.cabType = cabType;
        this.totalCharge = totalCharge;
        this.officePickupTime = officePickupTime;
        this.status = status;
        this.buddyProfiles = buddyProfiles;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public CabType getCabType() {
        return cabType;
    }

    public int getTotalCharge() {
        return totalCharge;
    }

    public LocalTime getOfficePickupTime() {
        return officePickupTime;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<BuddyProfile> getBuddyProfiles() {
        return buddyProfiles;
    }
    public String getStartPoint(){
        return buddyProfiles.get(0).getPickupPoint();
    }
    public String getStartTime(){
        return buddyProfiles.get(0).getPickupTime();
    }
    public int buddyCount(){
        return buddyProfiles.size();
    }
}
