package smartpool.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import smartpool.common.Constants;

import java.util.ArrayList;

public class Carpool {
    private String name;
    private LocalDate startDate;
    private CabType cabType;
    private LocalTime officeETA;
    private LocalTime officeETD;

    private int totalCabCharges;
    private Status status;
    private ArrayList<Buddy> buddies;
    private int capacity;
    private ArrayList<String> routePlan;

    public Carpool() {

        status = Status.PENDING;
    }

    public Carpool(String name, LocalDate startDate, String pickupPoint, CabType cabType, int totalCabCharges, LocalTime officeETA, LocalTime officeETD, Status status, ArrayList<Buddy> buddies, int capacity, ArrayList<String> routePlan) {
        this.name = name;
        this.startDate = startDate;
        this.cabType = cabType;
        this.totalCabCharges = totalCabCharges;
        this.officeETA = officeETA;
        this.officeETD = officeETD;
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

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : LocalDate.parse(startDate, Constants.DATE_FORMATTER);
    }

    public CabType getCabType() {
        return cabType;
    }

    public void setCabType(CabType cabType) {
        this.cabType = cabType;
    }

    public LocalTime getOfficeETA() {
        return officeETA;
    }

    public void setOfficeETA(String officeETA) {
        this.officeETA =  new  LocalTime(officeETA);
    }

    public LocalTime getOfficeETD() {
        return officeETD;
    }

    public void setOfficeETD(String officeETD) {
        this.officeETD = new  LocalTime(officeETD);
    }

    public int getTotalCabCharges() {
        return totalCabCharges;
    }

    public void setTotalCabCharges(int totalCabCharges) {
        this.totalCabCharges = totalCabCharges;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Buddy> getBuddies() {
        return buddies;
    }

    public void setBuddies(ArrayList<Buddy> buddies) {
        this.buddies = buddies;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
