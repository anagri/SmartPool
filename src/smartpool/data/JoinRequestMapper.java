package smartpool.data;

import org.apache.ibatis.annotations.Select;
import smartpool.domain.JoinRequest;

public interface JoinRequestMapper {

    static final String INSERT_REQUEST = "insert into requests values( #{username}, #{carpoolName}, " +
            "#{preferredPickupTime,javaType=org.joda.time.LocalTime, jdbcType=TIME, typeHandler=smartpool.data.typeHandler.LocalTimeTypeHandler}," +
            " #{pickupPoint}, #{address});";
    static final String SELECT_REQUEST_COUNT = "select count(*) from requests where username = #{param1} and carpoolname = #{param2};";
    static final String GET_MY_REQUEST = "select * from requests where username = #{param1} and carpoolname = #{param2};";

    @Select(INSERT_REQUEST)
    void insertRequest(JoinRequest joinRequest);

    @Select(SELECT_REQUEST_COUNT)
    boolean selectRequestCount(String userName, String carpoolName);

    @Select(GET_MY_REQUEST)
    JoinRequest selectUserRequest(String userName, String carpoolName);
}
