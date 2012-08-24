package smartpool.data;

import org.apache.ibatis.annotations.Select;
import smartpool.domain.Buddy;

import java.util.List;

public interface BuddyMapper {


    static final String SELECT_BUDDY = "select username, name, phone as contactNumber, email as emailId, address from buddies where username=#{username}";
    static final String SELECT_ALL="select * from buddies";

    @Select(SELECT_ALL)
    List<Buddy> selectAll();

    @Select(SELECT_BUDDY)
   Buddy selectBuddy(String username);


}
