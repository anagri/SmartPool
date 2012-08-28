package smartpool.data;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import smartpool.domain.JoinRequest;

public interface JoinRequestMapper {
    static final String INSERT_REQUEST = "insert into requests values( #{username}, #{carpoolName}, #{pickupTime}, #{pickupPoint});";
    @Select(INSERT_REQUEST)
    @Options(keyColumn = "[username,carpoolname]")
    void insertRequest(JoinRequest joinRequest);
}
