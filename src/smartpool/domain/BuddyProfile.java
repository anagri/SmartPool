package smartpool.domain;

public class BuddyProfile {
    private String name;
    private String address;
    private String contactNumber;
    private String emailId;

    public BuddyProfile(String name, String address, String contactNumber, String emailId, String preferredPickUpPoint) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.preferredPickUpPoint = preferredPickUpPoint;
    }

    private String preferredPickUpPoint;

    public String name() {
        return name;
    }


    public String address() {
        return address;
    }

    public String contactNumber() {
        return contactNumber;
    }


    public String emailId() {
        return emailId;
    }

    public String preferredPickUpPoint() {
        return preferredPickUpPoint;
    }

}
