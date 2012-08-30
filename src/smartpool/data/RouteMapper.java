package smartpool.data;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RouteMapper {
    final static String SELECT_ALL_CARPOOL_NAMES = "select carpoolName from route where location=#{location}";

    @Select(SELECT_ALL_CARPOOL_NAMES)
    public List<String> getCarpoolNameList(String location);
}
