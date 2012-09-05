package smartpool.web.form;

import smartpool.domain.Buddy;
import smartpool.domain.JoinRequest;

public class JoinRequestForm {
    String username;
    String carpoolName;
    String address;
    String pickupPoint;
    String preferredPickupTime;
    String contactNumber;
    String emailId;

    @SuppressWarnings("unused - used by spring data binding")
    public JoinRequestForm() {
    }

    public JoinRequestForm(String username, String carpoolName, String pickupPoint, String preferredPickupTime) {
        this.username = username;
        this.carpoolName = carpoolName;
        this.pickupPoint = pickupPoint;
        this.preferredPickupTime = preferredPickupTime;
    }

    public JoinRequestForm(Buddy buddy, String carpoolName) {
        this(buddy.getUserName(), carpoolName, buddy.getPickupPoint(), buddy.getPickupTime() == null ? "" : buddy.getPickupTime().toString("h:mm"));
    }

    public JoinRequest createDomainObject() {
        return null;
    }

    @SuppressWarnings("unused - used by spring data binding")
    public void setUsername(String username) {
        this.username = username;
    }

    @SuppressWarnings("unused - used by spring data binding")
    public void setCarpoolName(String carpoolName) {
        this.carpoolName = carpoolName;
    }

    @SuppressWarnings("unused - used by spring data binding")
    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    @SuppressWarnings("unused - used by spring data binding")
    public void setPreferredPickupTime(String preferredPickupTime) {
        this.preferredPickupTime = preferredPickupTime;
    }

    @SuppressWarnings("unused - used by spring data binding")
    public void setAddress(String address) {
        this.address = address;
    }

    @SuppressWarnings("unused - used by spring data binding")
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @SuppressWarnings("unused - used by spring data binding")
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getUsername() {
        return username;
    }

    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getCarpoolName() {
        return carpoolName;
    }

    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getPickupPoint() {
        return pickupPoint;
    }

    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getPreferredPickupTime() {
        return preferredPickupTime;
    }

    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getAddress() {
        return address;
    }

    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getContactNumber() {
        return contactNumber;
    }

    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getEmailId() {
        return emailId;
    }
}
