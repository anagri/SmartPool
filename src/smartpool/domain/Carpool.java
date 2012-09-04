package smartpool.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;

public class Carpool {
    private String name;

//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
//    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime officeETA;
//    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime officeETD;

    private int totalCabCharges;
    private CabType cabType;
    private Status status;
    private ArrayList<Buddy> buddies = new ArrayList<Buddy>();
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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public CabType getCabType() {
        return cabType;
    }

    public void setCabType(CabType cabType) {
        this.cabType = cabType;
    }

    @DateTimeFormat(pattern = "HH:mm")
    public LocalTime getOfficeETA() {
        return officeETA;
    }

    public void setOfficeETA(LocalTime officeETA) {
        this.officeETA = officeETA;
    }

    @DateTimeFormat(pattern = "HH:mm")
    public LocalTime getOfficeETD() {
        return officeETD;
    }

    public void setOfficeETD(LocalTime officeETD) {
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

    public String getStartPoint() {
        return buddies.get(0).getPickupPoint();
    }

    public LocalTime getStartTime() {
        return buddies.get(0).getPickupTime();
    }

    public int getBuddyCount() {
        return buddies.size();
    }

    public int getVacancy() {
        return this.capacity - getBuddyCount();
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
