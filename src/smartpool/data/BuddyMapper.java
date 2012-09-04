package smartpool.data;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import smartpool.domain.Buddy;

import java.util.ArrayList;
import java.util.List;

public interface BuddyMapper {

    static final String SELECT_BUDDY = "select username, name, phone as contactNumber, email as emailId, address, pickup_location as pickupPoint, pickup_time as pickupTime  from buddies where username=#{username};";
    static final String SELECT_ALL="select  username, name, phone as contactNumber, email as emailId, address, pickup_location as pickupPoint, pickup_time as pickupTime  from buddies;";
    static final String SELECT_BUDDY_LIST_BY_CARPOOL_NAME = "select  username, name, phone as contactNumber, email as emailId, address, pickup_location as pickupPoint, pickup_time as pickupTime  from buddies where carpoolname=#{carpoolName} order by pickup_time";
    static final String ADD_BUDDY_TO_CARPOOL = "update buddies set carpoolname=#{carpoolName} where username=#{userName}";

    @Select(SELECT_ALL)
    List<Buddy> selectAll();

    @Select(SELECT_BUDDY)
    Buddy selectBuddy(String username);

    @Select(SELECT_BUDDY_LIST_BY_CARPOOL_NAME)
    ArrayList<Buddy> selectBuddyListByCarpoolName(String carpoolName);

    @Update(ADD_BUDDY_TO_CARPOOL)
    void addToCarpool(@Param("userName") String userName, @Param("carpoolName") String carpoolName);

}
