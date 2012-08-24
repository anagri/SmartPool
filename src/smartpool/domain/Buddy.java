package smartpool.domain;

public class Buddy {
<<<<<<< Updated upstream

    String username, name, address, contactNumber, emailId, pickupTime;
    String[] preferredPickupPoints;

    public Buddy(){
    }



    public Buddy(String username) {
        this.username=username;
    }

    public Buddy(String username,String name, String contactNumber, String emailId,String address)
    {
        System.out.println("buddy create");
        this.username=username;
        this.name=name;
        this.address=address;
        this.emailId=emailId;
        this.contactNumber=contactNumber;
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
=======
    String username, name, address, contact_number, email_id, pickup_time;
    String[] preferred_pickup_points;
>>>>>>> Stashed changes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

<<<<<<< Updated upstream
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String[] getPreferredPickupPoints() {
        return preferredPickupPoints;
    }

    public void setPreferredPickupPoints(String[] preferredPickupPoints) {
        this.preferredPickupPoints = preferredPickupPoints;
=======
    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String[] getPreferred_pickup_points() {
        return preferred_pickup_points;
    }

    public void setPreferred_pickup_points(String[] preferred_pickup_points) {
        this.preferred_pickup_points = preferred_pickup_points;
>>>>>>> Stashed changes
    }
}
