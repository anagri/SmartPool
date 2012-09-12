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
            "(name,start_date, cab_type, office_eta, office_etd, status, capacity, request_sent) values(" +
            "#{name}," +
            "#{startDate, javaType=org.joda.time.LocalDate, jdbcType=DATE, typeHandler=smartpool.data.typeHandler.LocalDateTypeHandler}," +
            "#{cabType}," +
            "#{officeETA, javaType=org.joda.time.LocalTime, jdbcType=TIME, typeHandler=smartpool.data.typeHandler.LocalTimeTypeHandler}," +
            "#{officeETD, javaType=org.joda.time.LocalTime, jdbcType=TIME, typeHandler=smartpool.data.typeHandler.LocalTimeTypeHandler}," +
            "#{status}," +
            "#{capacity}," +
            "#{requestSent}"+
            ")";
    static final String SELECT_BY_NAME = "select name,start_date, office_eta,office_etd, cab_type,total_cab_charge,status,capacity,request_sent from carpool where name = #{name}";
    static final String DELETE_BY_NAME = "delete from carpool where name=#{name}";
    static final String SELECT_ALL = "select name,date_format(start_date, '%d/%m/%Y') \"start_date\", office_eta,office_etd, cab_type,total_cab_charge,status,capacity,request_sent from carpool";
    static final String UPDATE_CARPOOL_SET_REQUEST_SENT = "update carpool set request_sent = #{requestSent} where name= #{name}";
    static final String UPDATE_CARPOOL_SET_STATUS = "update carpool set status = #{status} where name= #{name}";

    @Select(SELECT_ALL)
    @Results(value = {
            @Result(property="name", column="name"),
            @Result(property="startDate", column="start_date"),
            @Result(property="cabType", column="cab_type"),
            @Result(property="totalCabCharges", column="total_cab_charge"),
            @Result(property="officeETA", column="office_eta", typeHandler = LocalTimeTypeHandler.class, javaType = LocalTime.class, jdbcType = JdbcType.TIME),
            @Result(property="officeETD", column="office_etd", typeHandler = LocalTimeTypeHandler.class, javaType = LocalTime.class, jdbcType = JdbcType.TIME),
            @Result(property="status", column="status"),
            @Result(property="capacity", column="capacity"),
            @Result(property="requestSent", column="request_sent")
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
            @Result(property="capacity", column="capacity"),
            @Result(property="requestSent", column="request_sent")
    })
    public Carpool get(String name);

    @Insert(INSERT)
    public void insert(Carpool carpool);

    @Delete(DELETE_BY_NAME)
    public void delete(String name);

    @Update(UPDATE_CARPOOL_SET_REQUEST_SENT)
    public void updateRequestSent(Carpool carpool);

    @Update(UPDATE_CARPOOL_SET_STATUS)
    public void updateStatus(Carpool carpool);
}
