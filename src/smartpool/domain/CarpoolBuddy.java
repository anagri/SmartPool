package smartpool.domain;

import org.joda.time.LocalTime;

public class CarpoolBuddy {
    Buddy buddy;
    String pickupPoint;
    LocalTime pickupTime;

    public CarpoolBuddy(Buddy buddy, String pickupPoint, LocalTime pickupTime) {
        this.buddy = buddy;
        this.pickupPoint = pickupPoint;
        this.pickupTime = pickupTime;
    }

    public Buddy getBuddy() {
        return buddy;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public LocalTime getPickupTime() {
        return pickupTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarpoolBuddy that = (CarpoolBuddy) o;

        if (buddy != null ? !buddy.equals(that.buddy) : that.buddy != null) return false;
        if (pickupPoint != null ? !pickupPoint.equals(that.pickupPoint) : that.pickupPoint != null) return false;
        if (pickupTime != null ? !pickupTime.equals(that.pickupTime) : that.pickupTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = buddy != null ? buddy.hashCode() : 0;
        result = 31 * result + (pickupPoint != null ? pickupPoint.hashCode() : 0);
        result = 31 * result + (pickupTime != null ? pickupTime.hashCode() : 0);
        return result;
    }
}
