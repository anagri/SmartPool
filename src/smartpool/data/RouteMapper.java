package smartpool.data;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface RouteMapper {

    final static String SELECT_ALL_CARPOOL_NAMES = "select distinct carpoolName from route,carpoolbuddy where location like #{location} or pickup_point like #{location} and carpoolName=carpool_name";
    final static String SELECT_ALL_LOCATIONS = "select distinct location from route union select distinct pickup_point from carpoolbuddy";
    final static String INSERT = "insert into route (carpoolName,location, sequenceNumber) values(#{param1}, #{param2}, #{param3})";
    String DELETE = "delete from route where carpoolName = #{param1} and location = #{param2}";

    @Select(SELECT_ALL_CARPOOL_NAMES)
    public List<String> getCarpoolNameList(String location);

    @Select(SELECT_ALL_LOCATIONS)
    public List<String> getAllLocations();

    @Insert(INSERT)
    public void insert(String carpoolName, String location, int sequenceNumber);

    @Delete(DELETE)
    void delete(String carpoolName, String location);

    @Select("select location from route where carpoolName = #{carpoolName} order by sequenceNumber")
    public ArrayList<String> getLocationsFor(String carpoolName);
}
