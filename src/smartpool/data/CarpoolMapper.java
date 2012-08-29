package smartpool.data;

import org.apache.ibatis.annotations.Insert;
import smartpool.domain.Carpool;

public interface CarpoolMapper {

    static final String INSERT = "insert into carpool values(#{name})";

    @Insert(INSERT)
    public int insert(Carpool carpool);
}
