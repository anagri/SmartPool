package smartpool.domain;

import org.joda.time.LocalTime;

public class JoinRequest {
    String username, carpoolName, pickupPoint;
    LocalTime preferredPickupTime;
    String address;

    @SuppressWarnings("unused - used by MyBatis")
    public JoinRequest() {
    }

    public JoinRequest(String username, String carpoolName, String address, String pickupPoint, LocalTime preferredPickupTime) {
        this.username = username;
        this.carpoolName = carpoolName;
        this.address = address;
        this.pickupPoint = pickupPoint;
        this.preferredPickupTime = preferredPickupTime;
    }

    public String getUsername() {
        return username;
    }

    public String getCarpoolName() {
        return carpoolName;
    }

    @Override
    public String toString() {
        return  "<table><tr><td><b>Carpool Name</b> </td><td>  :  " + carpoolName +"</td></tr><br /><br />"+
                "<tr><td><b>Pick-Up Point</b> </td><td>  :  " + pickupPoint +"</td></tr><br /><br />"+
                "<tr><td><b>Preferred Pickup Time</b> </td><td>  :  " + preferredPickupTime.toString().substring(0,5) +"</td></tr><br /><br />"+
                "<tr><td><b>Address</b> </td><td>  :  " + address+"</td></tr></table><br /><br />";
    }
}
