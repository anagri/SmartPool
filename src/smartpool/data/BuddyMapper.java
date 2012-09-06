package smartpool.data;

import org.apache.ibatis.annotations.*;
import smartpool.data.typeHandler.LocalTimeTypeHandler;
import smartpool.domain.Buddy;

import java.util.ArrayList;
import java.util.List;

public interface BuddyMapper {

    static final String SELECT_BUDDY = "select username, name, phone as contactNumber, email as emailId, address, pickup_location as pickupPoint, pickup_time as pickupTime  from buddies where username=#{username};";
    static final String SELECT_ALL="select  username, name, phone as contactNumber, email as emailId, address, pickup_location as pickupPoint, pickup_time as pickupTime  from buddies;";
    static final String SELECT_BUDDY_LIST_BY_CARPOOL_NAME = "select  username, name, phone, email, address, pickup_location, pickup_time from buddies where carpoolname=#{carpoolName} order by pickup_time";
    static final String ADD_BUDDY_TO_CARPOOL = "update buddies set carpoolname=#{carpoolName} where username=#{userName}";
    static final String BUDDY_EXISTS = "select count(username) from buddies where username=#{username};";

    @Select(SELECT_ALL)
    List<Buddy> selectAll();

    @Select(SELECT_BUDDY)
    Buddy selectBuddy(String username);

    @Select(SELECT_BUDDY_LIST_BY_CARPOOL_NAME)
    @Results(value = {
            @Result(property="username", column="username"),
            @Result(property="name", column="name"),
            @Result(property="contactNumber", column="phone"),
            @Result(property="emailId", column="email"),
            @Result(property="address", column="address"),
            @Result(property="pickupPoint", column="pickup_location"),
            @Result(property="pickupTime", column="pickup_time", typeHandler = LocalTimeTypeHandler.class)
    })
    ArrayList<Buddy> selectBuddyListByCarpoolName(String carpoolName);

    @Update(ADD_BUDDY_TO_CARPOOL)
    void addToCarpool(@Param("userName") String userName, @Param("carpoolName") String carpoolName);

    @Select(BUDDY_EXISTS)
    boolean exists(String username);
}
