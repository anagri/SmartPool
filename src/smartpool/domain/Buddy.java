package smartpool.domain;

public class Buddy {

    private String username;
    private String name;
    private String address;
    private String contactNumber;
    private String emailId;

    private Buddy() {
    }

    public Buddy(String username) {
        this.username = username;
    }

    public Buddy(String username, String name, String contactNumber, String emailId, String address) {
        this(username);
        this.name = name;
        this.address = address;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
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
