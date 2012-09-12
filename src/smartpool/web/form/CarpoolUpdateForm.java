package smartpool.web.form;

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
}
