package smartpool.data;

<<<<<<< Updated upstream
import org.apache.ibatis.annotations.Select;
=======
import org.apache.ibatis.annotations.*;
>>>>>>> Stashed changes
import smartpool.domain.Buddy;

import java.util.List;

public interface BuddyMapper {

<<<<<<< Updated upstream
    static final String SELECT_BUDDY = "select username, name, phone as contactNumber, email as emailId, address from buddies where username=#{username}";
    static final String SELECT_ALL="select * from buddies";

    @Select(SELECT_ALL)
    List<Buddy> selectAll();

    @Select(SELECT_BUDDY)
   Buddy selectBuddy(String username);
=======
    final String SELECT_BUDDY = "select * from buddies where username=#{username}";


    @Select(SELECT_BUDDY)
    @Results(value = {
            @Result(property="name", column="name"),
            @Result(property="address", column="address"),
            @Result(property="contact_number", column="phone"),
            @Result(property="email", column="email")
    })
    Buddy selectBuddy();


>>>>>>> Stashed changes
}
