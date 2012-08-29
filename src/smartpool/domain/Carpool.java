package smartpool.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;

public class Carpool {
    final String name;

    LocalDate startDate;

    String pickupPoint;
    CabType cabType;
    int totalCabCharges;
    LocalTime officePickupTime;
    Status status;
    ArrayList<Buddy> buddies;
    int capacity;
    ArrayList<String> routePlan;
    private LocalTime officeETA;

    public Carpool(String name, LocalDate startDate, String pickupPoint, CabType cabType, int totalCabCharges, LocalTime officeETA, LocalTime officePickupTime, Status status, ArrayList<Buddy> buddies, int capacity, ArrayList<String> routePlan) {
        this.name = name;
        this.startDate = startDate;
        this.pickupPoint = pickupPoint;
        this.cabType = cabType;
        this.totalCabCharges = totalCabCharges;
        this.officeETA = officeETA;
        this.officePickupTime = officePickupTime;
        this.status = status;
        this.buddies = buddies;
        this.capacity = capacity;
        this.routePlan = routePlan;
    }

    public Carpool(String name) {
        this.name = name;
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

    public ArrayList<Buddy> getBuddies() {
        return buddies;
    }

    public String getStartPoint(){
        return buddies.get(0).getPickupPoint();
    }
    public String getStartTime(){
        return buddies.get(0).getPickupTime();
    }
    public int buddyCount(){
        return buddies.size();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carpool carpool = (Carpool) o;

        if (name != null ? !name.equals(carpool.name) : carpool.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
