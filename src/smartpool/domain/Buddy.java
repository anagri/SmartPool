package smartpool.domain;

import org.joda.time.LocalTime;

public class Buddy {

    private String username;
    private String name;
    private String address;
    private String contactNumber;
    private String preferredPickupPoint;
    private LocalTime preferredPickupTime;
    private String emailId;

    private Buddy() {
    }

    public Buddy(String username) {
        this.username = username;
    }

    public Buddy(String username, String name, String contactNumber, String emailId, String address, String preferredPickupPoint, LocalTime preferredPickupTime) {
        this(username);
        this.name = name;
        this.address = address;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
        this.preferredPickupPoint = preferredPickupPoint;
        this.preferredPickupTime = preferredPickupTime;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getUserName() {
        return username;
    }

    public String getPreferredPickupPoint() {
        return preferredPickupPoint;
    }

    public LocalTime getPreferredPickupTime() {
        return preferredPickupTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buddy buddy = (Buddy) o;

        if (username != null ? !username.equals(buddy.username) : buddy.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
