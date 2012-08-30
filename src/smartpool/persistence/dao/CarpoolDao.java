package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.CarpoolMapper;
import smartpool.domain.Carpool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CarpoolDao {

    private SqlSessionFactory sqlSessionFactory;
    private HttpServletRequest request;

    public CarpoolDao() {
        this(MyBatisConnectionFactory.getSqlSessionFactory());
    }

    public CarpoolDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    public void insert(Carpool carpool) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CarpoolMapper carpoolMapper = sqlSession.getMapper(CarpoolMapper.class);
        carpoolMapper.insert(carpool);
        sqlSession.commit();
        sqlSession.close();
    }

    public Carpool get(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CarpoolMapper carpoolMapper = sqlSession.getMapper(CarpoolMapper.class);
        return carpoolMapper.get(name);

    }

    public void delete(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CarpoolMapper carpoolMapper = sqlSession.getMapper(CarpoolMapper.class);
        carpoolMapper.delete(name);
        sqlSession.commit();
        sqlSession.close();
    }

    public List<Carpool> selectAllCarpools() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            CarpoolMapper mapper = session.getMapper(CarpoolMapper.class);
            List<Carpool> carpools = mapper.selectAll();
            return carpools;
        } finally {
            session.close();
        }
    }

    public List<String> selectCarpoolByLocation(String location) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            CarpoolMapper mapper = session.getMapper(CarpoolMapper.class);
            List<String> carpools = mapper.selectByLocation(location);
            return carpools;
        } finally {
            session.close();
        }
    }
}
