package smartpool.data;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import smartpool.data.typeHandler.LocalDateTypeHandler;
import smartpool.data.typeHandler.LocalTimeTypeHandler;
import smartpool.domain.Carpool;

import java.util.List;

public interface CarpoolMapper {

    static final String INSERT = "insert into carpool " +
            "(name,start_date, cab_type, office_eta, office_etd, status) values(" +
            "#{name}," +
            "#{startDate, javaType=org.joda.time.LocalDate, jdbcType=DATE, typeHandler=smartpool.data.typeHandler.LocalDateTypeHandler}," +
            "#{cabType}," +
            "#{officeETA, javaType=org.joda.time.LocalTime, jdbcType=TIME, typeHandler=smartpool.data.typeHandler.LocalTimeTypeHandler}," +
            "#{officeETD, javaType=org.joda.time.LocalTime, jdbcType=TIME, typeHandler=smartpool.data.typeHandler.LocalTimeTypeHandler}," +
            "#{status}" +
            ")";
    static final String SELECT_BY_NAME = "select name,start_date, office_eta,office_etd, cab_type,total_cab_charge,status,capacity from carpool where name = #{name}";
    static final String DELETE_BY_NAME = "delete from carpool where name=#{name}";
    static final String SELECT_ALL = "select name,date_format(start_date, '%d/%m/%Y') \"start_date\", office_eta,office_etd, cab_type,total_cab_charge,status,capacity  from carpool";

    @Select(SELECT_ALL)
    @Results(value = {
            @Result(property="name", column="name"),
            @Result(property="startDate", column="start_date"),
            @Result(property="cabType", column="cab_type"),
            @Result(property="totalCabCharges", column="total_cab_charge"),
            @Result(property="officeETA", column="office_eta", typeHandler = LocalTimeTypeHandler.class, javaType = LocalTime.class, jdbcType = JdbcType.TIME),
            @Result(property="officeETD", column="office_etd", typeHandler = LocalTimeTypeHandler.class, javaType = LocalTime.class, jdbcType = JdbcType.TIME),
            @Result(property="status", column="status"),
            @Result(property="capacity", column="capacity")
    })
    List<Carpool> selectAll();

    @Select(SELECT_BY_NAME)
    @Results(value = {
            @Result(property="name", column="name"),
            @Result(property="startDate", column="start_date", javaType = LocalDate.class, jdbcType = JdbcType.DATE, typeHandler = LocalDateTypeHandler.class),
            @Result(property="cabType", column="cab_type"),
            @Result(property="totalCabCharges", column="total_cab_charge"),
            @Result(property="officeETA", column="office_eta", typeHandler = LocalTimeTypeHandler.class, javaType = LocalTime.class, jdbcType = JdbcType.TIME),
            @Result(property="officeETD", column="office_etd", typeHandler = LocalTimeTypeHandler.class, javaType = LocalTime.class, jdbcType = JdbcType.TIME),
            @Result(property="status", column="status"),
            @Result(property="capacity", column="capacity")
    })
    public Carpool get(String name);

    @Insert(INSERT)
    public void insert(Carpool carpool);

    @Delete(DELETE_BY_NAME)
    void delete(String name);
}
