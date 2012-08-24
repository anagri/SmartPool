package smartpool.domain;

public class BuddyProfile {
    public final String name;
    public final String address;
    public final String contactNumber;
    public final String emailId;
    public final String preferredPickUpPoint;

    public BuddyProfile(String name, String address, String contactNumber, String emailId, String preferredPickUpPoint) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.preferredPickUpPoint = preferredPickUpPoint;
    }
}
