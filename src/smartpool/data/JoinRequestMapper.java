package smartpool.data;

import org.apache.ibatis.annotations.Select;
import smartpool.domain.JoinRequest;

public interface JoinRequestMapper {
    static final String INSERT_REQUEST = "insert into requests values( #{username}, #{carpoolName}, #{pickupTime}, #{pickupPoint});";
    @Select(INSERT_REQUEST)
    void insertRequest(JoinRequest joinRequest);
}
