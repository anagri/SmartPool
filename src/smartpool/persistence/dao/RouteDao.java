package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.RouteMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class RouteDao {
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    public RouteDao() {
        this(MyBatisConnectionFactory.getSqlSessionFactory());
    }

    public RouteDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<String> getCarpoolNameListByLocation(String location) {
        sqlSession = sqlSessionFactory.openSession();
        RouteMapper routeMapper = sqlSession.getMapper(RouteMapper.class);

        return routeMapper.getCarpoolNameList("%" + location + "%");
    }

    public List<String> getAllLocations() {
        sqlSession = sqlSessionFactory.openSession();
        RouteMapper routeMapper = sqlSession.getMapper(RouteMapper.class);
        return routeMapper.getAllLocations();
    }


    public void insert(String carpoolName, String routePoints, int sequenceNumber) {
        sqlSession = sqlSessionFactory.openSession();
        RouteMapper routeMapper = sqlSession.getMapper(RouteMapper.class);
        routeMapper.insert(carpoolName, routePoints, sequenceNumber);
        sqlSession.commit();
    }

    public void delete(String carpoolName, String location) {
        sqlSession = sqlSessionFactory.openSession();
        RouteMapper routeMapper = sqlSession.getMapper(RouteMapper.class);
        routeMapper.delete(carpoolName, location);
        sqlSession.commit();
    }

    public ArrayList<String> getLocationsFor(String carpoolName) {
        sqlSession = sqlSessionFactory.openSession();
        RouteMapper routeMapper = sqlSession.getMapper(RouteMapper.class);
        return routeMapper.getLocationsFor(carpoolName);
    }
}
