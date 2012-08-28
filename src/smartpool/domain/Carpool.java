package smartpool.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;

public class Carpool {
    final String name;

    final LocalDate startDate;

    final String pickupPoint;
    final CabType cabType;
    final int totalCabCharges;
    final LocalTime officePickupTime;
    final Status status;
    final ArrayList<Buddy> buddyProfiles;
    private final int capacity;
    private final ArrayList<String> routePlan;
    private LocalTime officeETA;

    public Carpool(String name, LocalDate startDate, String pickupPoint, CabType cabType, int totalCabCharges, LocalTime officeETA, LocalTime officePickupTime, Status status, ArrayList<Buddy> buddyProfiles, int capacity, ArrayList<String> routePlan) {
        this.name = name;
        this.startDate = startDate;
        this.pickupPoint = pickupPoint;
        this.cabType = cabType;
        this.totalCabCharges = totalCabCharges;
        this.officeETA = officeETA;
        this.officePickupTime = officePickupTime;
        this.status = status;
        this.buddyProfiles = buddyProfiles;
        this.capacity = capacity;
        this.routePlan = routePlan;
    }

    public String getName() {
        return name;
    }

    public LocalTime getOfficeETA(){
        return officeETA;
    }

    public CabType getCabType() {
        return cabType;
    }

    public int getTotalCabCharges() {
        return totalCabCharges;
    }

    public LocalTime getOfficePickupTime() {
        return officePickupTime;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Buddy> getBuddyProfiles() {
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
    public LocalDate getStartDate() {
        return startDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<String> getRoutePlan() {
        return routePlan;
    }
}
