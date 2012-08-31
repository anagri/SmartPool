package smartpool.data;

import org.apache.ibatis.annotations.Select;
import smartpool.domain.JoinRequest;

public interface JoinRequestMapper {
    static final String INSERT_REQUEST = "insert into requests values( #{username}, #{carpoolName}, #{pickupTime}, #{pickupPoint});";
    static final String SELECT_REQUEST_COUNT = "select count(*) from requests where username = #{param1} and carpoolname = #{param2};";

    @Select(INSERT_REQUEST)
    void insertRequest(JoinRequest joinRequest);

    @Select(SELECT_REQUEST_COUNT)
    boolean selectRequestCount(String userName, String carpoolName);
}
