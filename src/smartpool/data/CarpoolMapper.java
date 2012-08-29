package smartpool.data;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import smartpool.domain.Carpool;

public interface CarpoolMapper {

    static final String INSERT = "insert into carpool (name) values(#{name})";
    static final String SELECT_BY_NAME = "select * from carpool where name = #{name}";
    static final String DELETE_BY_NAME = "delete from carpool where name=#{name}";


    @Insert(INSERT)
    public void insert(Carpool carpool);

    @Select(SELECT_BY_NAME)
    public Carpool get(String name);

    @Delete(DELETE_BY_NAME)
    void delete(String name);
}
