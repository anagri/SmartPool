package smartpool.data;
import org.apache.ibatis.annotations.*;

public class BuddyMapper {
    @Select(SELECT_BY_ID)
    @Results(value = {
            @Result(property="id"),
            @Result(property="name", column="CONTACT_NAME"),
            @Result(property="phone", column="CONTACT_PHONE"),
            @Result(property="email", column="CONTACT_EMAIL")
    })
    Contact selectById(int id);

}
