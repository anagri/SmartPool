package smartpool.domain;

public class JoinRequest {

    String username, carpoolName, pickupPoint, pickupTime;

    public JoinRequest(String buddyName, String carpoolName, String pickupPoint, String pickupTime) {
        this.username = buddyName;
        this.carpoolName = carpoolName;
        this.pickupPoint = pickupPoint;
        this.pickupTime = pickupTime;
    }

    public JoinRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarpoolName() {
        return carpoolName;
    }

    public void setCarpoolName(String carpoolName) {
        this.carpoolName = carpoolName;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }
}
