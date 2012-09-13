package smartpool.data;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.LocalTime;
import smartpool.data.typeHandler.LocalTimeTypeHandler;
import smartpool.domain.JoinRequest;

public interface JoinRequestMapper {

    static final String INSERT_REQUEST = "insert into requests values( #{username}, #{carpoolName}, " +
            "#{preferredPickupTime,javaType=org.joda.time.LocalTime, jdbcType=TIME, typeHandler=smartpool.data.typeHandler.LocalTimeTypeHandler}," +
            " #{pickupPoint}, #{address});";
    static final String SELECT_REQUEST_COUNT = "select count(*) from requests where username = #{param1} and carpoolname = #{param2};";
    static final String GET_MY_REQUEST = "select * from requests where username = #{param1} and carpoolname = #{param2};";
    static final String DELETE_USERS_REQUEST = "delete from requests where username = #{param1} and carpoolname = #{param2}";
    static final String INSERT_REQUEST_UID = "insert into pending_request values( #{param1}, #{param2}, #{param3}); ";
    static final String GET_REQUEST_UID = "select uid from pending_request where username = #{param1} and carpoolname = #{param2}; ";
    static final String DELETE_PENDING_REQUEST = "delete from pending_request where uid = #{param1}";
    static String SELECT_BUDDY_USERNAME = "select username from pending_request where uid = #{param1}";
    static String SELECT_CARPOOL_NAME = "select carpoolname from pending_request where uid = #{param1} ";
    static String UID_PRESENT = "select count(uid) from pending_request where uid = #{param1} ";

    @Select(INSERT_REQUEST)
    void insertRequest(JoinRequest joinRequest);

    @Select(SELECT_REQUEST_COUNT)
    boolean selectRequestCount(String userName, String carpoolName);

    @Select(GET_MY_REQUEST)
    @Results(value = {
            @Result(property = "username",column = "username"),
            @Result(property = "carpoolName",column = "carpoolname"),
            @Result(property = "pickupPoint", column = "preferredPickupPoint"),
            @Result(property = "preferredPickupTime", column = "preferredPickupTime", javaType = LocalTime.class, jdbcType = JdbcType.TIME, typeHandler = LocalTimeTypeHandler.class)
    })
    JoinRequest selectUserRequest(String userName, String carpoolName);

    @Delete(DELETE_USERS_REQUEST)
    void deleteUserRequest(String buddyUsername, String carpoolName);

    @Select(INSERT_REQUEST_UID)
    void addUniqueIdToPendingRequest(String buddyUsername, String carpoolName, String uuid);

    @Select(GET_REQUEST_UID)
    String getUniqueIdToPendingRequest(String buddyUsername, String carpoolName);

    @Delete(DELETE_PENDING_REQUEST)
    void deletePendingRequest(String uid);

    @Select(SELECT_BUDDY_USERNAME)
    String getBuddyUserNameFromUid(String uid);

    @Select(SELECT_CARPOOL_NAME)
    String getCarpoolNameFromUid(String uid);

    @Select(UID_PRESENT)
    Boolean isUidPresent(String uid);
}
