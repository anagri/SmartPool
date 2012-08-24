package smartpool.domain;

public class BuddyProfile {
    private String name;
    private String address;
    private String contactNumber;
    private String emailId;
    private String preferredPickUpPoint;
    private String pickupPoint;
    private String pickupTime;

    public BuddyProfile(String name, String address, String contactNumber, String emailId, String preferredPickUpPoint) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.preferredPickUpPoint = preferredPickUpPoint;
    }

    public String getName() {
        return name;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }
}
