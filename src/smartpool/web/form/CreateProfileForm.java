package smartpool.web.form;

import org.joda.time.LocalTime;
import smartpool.domain.Buddy;

public class CreateProfileForm {
    String username;
    String name;
    String preferredPickupPoint;
    String preferredPickupTime;
    String address;
    String contactNumber;
    String email;

    @SuppressWarnings("unused - used by spring data binding")
    public CreateProfileForm() {
    }

    public CreateProfileForm(String username, String name, String email, String address, String contactNumber, String preferredPickupPoint, String preferredPickupTime) {
        this.username = username;
        this.name = name;
        this.preferredPickupPoint = preferredPickupPoint;
        this.preferredPickupTime = preferredPickupTime;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public Buddy createBuddy() {
        return new Buddy(username, name, contactNumber, email, address, preferredPickupPoint, LocalTime.parse(preferredPickupTime));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }
    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public void setpreferredPickupPoint(String preferredPickupPoint) {
        this.preferredPickupPoint = preferredPickupPoint;
    }
    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public void setPreferredPickupTime(String preferredPickupTime) {
        this.preferredPickupTime = preferredPickupTime;
    }
    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public void setAddress(String address) {
        this.address = address;
    }

    @SuppressWarnings("unused - used by spring data binding")
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getUsername() {
        return username;
    }

    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getName() {
        return name;
    }
    @SuppressWarnings("unused - used to render pre populated form in jsp")
    public String getpreferredPickupPoint() {
        return preferredPickupPoint;
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
    public String getEmail() {
        return email;
    }
}
