package smartpool.data;

import org.apache.ibatis.annotations.Select;
import smartpool.domain.JoinRequest;

public interface RequestMapper {
    static final String INSERT_INTO_REQUESTS = "insert into requests values(#{userName}, #{carpoolName},);";

    @Select(INSERT_INTO_REQUESTS)
    void insertRequest(JoinRequest request);

}
