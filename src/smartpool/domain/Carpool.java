package smartpool.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class Carpool implements Comparable {
    private String name;

    private LocalDate startDate;
    private LocalTime officeETA;
    private LocalTime officeETD;

    private int totalCabCharges;
    private CabType cabType;
    private Status status;
    private List<CarpoolBuddy> carpoolBuddies = new ArrayList<CarpoolBuddy>();
    private int capacity;
    private List<String> routePoints = new ArrayList<String>();
    private boolean requestSent;

    public Carpool() {
        status = Status.NOT_STARTED;
        capacity = 1;
    }

    public Carpool(String name, LocalDate startDate, CabType cabType, int totalCabCharges,
                   LocalTime officeETA, LocalTime officeETD, Status status, List<CarpoolBuddy> carpoolBuddies,
                   int capacity, List<String> routePoints) {
        this.name = name;
        this.startDate = startDate;
        this.cabType = cabType;
        this.totalCabCharges = totalCabCharges;
        this.officeETA = officeETA;
        this.officeETD = officeETD;
        this.status = status;
        this.carpoolBuddies = carpoolBuddies;
        this.capacity = capacity;
        this.routePoints = routePoints;
    }

    public Carpool(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFrom(){
        String from = name.split("-")[0];
        return from.substring(0,1).toUpperCase()+from.substring(1);
    }

    public String getTo(){
        String to = name.split("-")[1];
        return to.trim().substring(0,1).toUpperCase()+to.trim().substring(1);
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public LocalTime getOfficeETA() {
        return officeETA;
    }

    public void setOfficeETA(LocalTime officeETA) {
        this.officeETA = officeETA;
    }

    public LocalTime getOfficeETD() {
        return officeETD;
    }

    public void setOfficeETD(LocalTime officeETD) {
        this.officeETD = new LocalTime(officeETD);
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

    public String getStatusAsString(){
        return status.toString().replaceAll("_", " ");
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<CarpoolBuddy> getCarpoolBuddies() {
        return carpoolBuddies;
    }

    public void setCarpoolBuddies(ArrayList<CarpoolBuddy> carpoolBuddies) {
        this.carpoolBuddies = carpoolBuddies;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(ArrayList<String> routePoints) {
        this.routePoints = routePoints;
    }

    public String getStartPoint() {
        return carpoolBuddies.get(0).getPickupPoint();
    }

    public LocalTime getStartTime() {
        return carpoolBuddies.get(0).getPickupTime();
    }

    public int getBuddyCount() {
        return carpoolBuddies.size();
    }

    public int getVacancy() {
        return this.capacity - getBuddyCount();
    }

    public boolean hasVacancy() {
        if(getVacancy()!=0)
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carpool carpool = (Carpool) o;

        if (capacity != carpool.capacity) return false;
        if (totalCabCharges != carpool.totalCabCharges) return false;
        if (cabType != carpool.cabType) return false;
        if (carpoolBuddies != null ? !carpoolBuddies.equals(carpool.carpoolBuddies) : carpool.carpoolBuddies != null)
            return false;
        if (name != null ? !name.equals(carpool.name) : carpool.name != null) return false;
        if (officeETA != null ? !officeETA.equals(carpool.officeETA) : carpool.officeETA != null) return false;
        if (officeETD != null ? !officeETD.equals(carpool.officeETD) : carpool.officeETD != null) return false;
        if (routePoints != null ? !routePoints.equals(carpool.routePoints) : carpool.routePoints != null) return false;
        if (startDate != null ? !startDate.equals(carpool.startDate) : carpool.startDate != null) return false;
        if (status != carpool.status) return false;

        return true;
    }

    public boolean canStart() {
        return Status.NOT_STARTED.equals(status) && getBuddyCount() > 1;
    }


    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (officeETA != null ? officeETA.hashCode() : 0);
        result = 31 * result + (officeETD != null ? officeETD.hashCode() : 0);
        result = 31 * result + totalCabCharges;
        result = 31 * result + (cabType != null ? cabType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (carpoolBuddies != null ? carpoolBuddies.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (routePoints != null ? routePoints.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        Carpool carpool = (Carpool) o;
        return this.status.compareTo(carpool.status);
    }

    public Boolean getRequestSent() {
        return requestSent;
    }

    public void setRequestSent(boolean requestSent) {
        this.requestSent = requestSent;
    }

    public String getStartLinkText() {
        if(!requestSent)
           return "Start It";

        return "Request Sent To Admin";
    }

    public String getStartLink() {
        if(!requestSent)
            return "/carpool/" + name + "/start";
        return "";
    }
}
