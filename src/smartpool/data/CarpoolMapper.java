package smartpool.data;

import org.apache.ibatis.annotations.*;
import smartpool.domain.Carpool;

public interface CarpoolMapper {

    static final String INSERT = "insert into carpool (name) values(#{name})";
    static final String SELECT_BY_NAME = "select * from carpool where name = #{name}";
    static final String DELETE_BY_NAME = "delete from carpool where name=#{name}";


    @Insert(INSERT)
    public void insert(Carpool carpool);

    @Select(SELECT_BY_NAME)
    @Results(value = {
        @Result(property="name", column="name"),
        @Result(property="startDate", column="start_date"),
        @Result(property="cabType", column="cab_type"),
        @Result(property="totalCabCharges", column="total_cab_charge"),
        @Result(property="officeETA", column="office_eta"),
        @Result(property="officePickupTime", column="office_etd"),
        @Result(property="status", column="status"),
        @Result(property="capacity", column="capacity")
    })
    public Carpool get(String name);

    @Delete(DELETE_BY_NAME)
    void delete(String name);
}
