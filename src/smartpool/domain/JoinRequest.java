package smartpool.domain;

public class JoinRequest {

    String userName, carpoolName, preferredPickupPoint, preferredPickupTime;

    public JoinRequest(String buddyName, String carpoolName, String preferredPickupPoint, String preferredPickupTime) {
        this.userName = buddyName;
        this.carpoolName = carpoolName;
        this.preferredPickupPoint = preferredPickupPoint;
        this.preferredPickupTime = preferredPickupTime;
    }
}
