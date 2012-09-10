package smartpool.data;

import org.apache.ibatis.annotations.*;
import smartpool.data.typeHandler.LocalTimeTypeHandler;
import smartpool.domain.Buddy;

import java.util.ArrayList;
import java.util.List;

public interface BuddyMapper {

    static final String SELECT_BUDDY = "select username, name, phone as contactNumber, email as emailId, address, preferred_pickup_location as preferredPickupPoint, preferred_pickup_time as preferredPickupTime from buddies where username=#{username}";
    static final String SELECT_ALL="select  username, name, phone as contactNumber, email as emailId, address   from buddies, carpoolbuddy where buddy_username = username";
    static final String SELECT_BUDDY_LIST_BY_CARPOOL_NAME = "select  username, name, phone, email, address from buddies, carpoolbuddy where carpool_name=#{carpoolName} and buddy_username = username order by pickup_time";
    static final String ADD_BUDDY_TO_CARPOOL = "insert into carpoolbuddy(buddy_username,carpool_name) values(#{userName},#{carpoolName})";
    static final String BUDDY_EXISTS = "select count(username) from buddies where username=#{username};";
    static final String INSERT_INTO_BUDDIES = "insert into buddies values(" +
            "#{username}, #{name}, #{address}, #{emailId}, #{contactNumber}, #{preferredPickupPoint}," +
            "#{preferredPickupTime, javaType=org.joda.time.LocalTime, jdbcType=TIME, typeHandler=smartpool.data.typeHandler.LocalTimeTypeHandler})";

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
            @Result(property="pickupPoint", column="pickup_point"),
            @Result(property="pickupTime", column="pickup_time", typeHandler = LocalTimeTypeHandler.class)
    })
    ArrayList<Buddy> selectBuddyListByCarpoolName(String carpoolName);

    @Insert(ADD_BUDDY_TO_CARPOOL)
    void addToCarpool(@Param("userName") String userName, @Param("carpoolName") String carpoolName);

    @Select(BUDDY_EXISTS)
    boolean exists(String username);

    @Select(INSERT_INTO_BUDDIES)
    void insertIntoBuddies(Buddy buddy);
}
