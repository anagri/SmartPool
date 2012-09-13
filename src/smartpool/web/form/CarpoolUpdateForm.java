package smartpool.web.form;

import smartpool.domain.Carpool;
import smartpool.domain.Status;

public class CarpoolUpdateForm {
    String status;
    String charges;
    String capacity;

    public CarpoolUpdateForm(String status, String charges, String capacity) {
        this.status = status;
        this.charges = charges;
        this.capacity = capacity;
    }

    public CarpoolUpdateForm() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Carpool createDomainObject(Carpool original) {
        Carpool updated = new Carpool(original.getName(), original.getStartDate(), original.getCabType(), original.getTotalCabCharges(), original.getOfficeETA(), original.getOfficeETD(), original.getStatus(), original.getCarpoolBuddies(), original.getCapacity(), original.getRoutePoints());
        updated.setStatus(Status.valueOf(status));
        updated.setCapacity(Integer.valueOf(capacity));
        updated.setTotalCabCharges(Integer.valueOf(charges));
        return updated;

    }
}
