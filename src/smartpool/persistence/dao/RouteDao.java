package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.RouteMapper;

import java.util.List;

public class RouteDao {
    private SqlSessionFactory sqlSessionFactory;

    public RouteDao() {
        this(MyBatisConnectionFactory.getSqlSessionFactory());
    }

    public RouteDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<String> getCarpoolNameListByLocation(String location) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        RouteMapper routeMapper = sqlSession.getMapper(RouteMapper.class);
        return routeMapper.getCarpoolNameList(location);
    }
}
