package smartpool.data;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
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


    @Select(INSERT_REQUEST)
    void insertRequest(JoinRequest joinRequest);

    @Select(SELECT_REQUEST_COUNT)
    boolean selectRequestCount(String userName, String carpoolName);

    @Select(GET_MY_REQUEST)
    JoinRequest selectUserRequest(String userName, String carpoolName);

    @Delete(DELETE_USERS_REQUEST)
    void deleteUserRequest(String buddyUsername, String carpoolName);

    @Select(INSERT_REQUEST_UID)
    void addUniqueIdToPendingRequest(String buddyUsername, String carpoolName, String uuid);

    @Select(GET_REQUEST_UID)
    String getUniqueIdToPendingRequest(String buddyUsername, String carpoolName);
}
