package smartpool.data;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface RouteMapper {
    final static String SELECT_ALL_CARPOOL_NAMES = "select distinct carpoolName from route where location like #{location}";
    final static String SELECT_ALL_LOCATIONS = "select distinct location from route";
    final static String INSERT = "insert into route (carpoolName,location) values(#{param1}, #{param2})";
    String DELETE = "delete from route where carpoolName = #{param1} and location = #{param2}";

    @Select(SELECT_ALL_CARPOOL_NAMES)
    public List<String> getCarpoolNameList(String location);

    @Select(SELECT_ALL_LOCATIONS)
    public List<String> getAllLocations();

    @Insert(INSERT)
    public void insert(String carpoolName, String location);

    @Delete(DELETE)
    void delete(String carpoolName, String location);

    @Select("select location from route where carpoolName = #{carpoolName}")
    public ArrayList<String> getLocationsFor(String carpoolName);
}
