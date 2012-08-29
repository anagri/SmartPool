package smartpool.persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smartpool.common.MyBatisConnectionFactory;
import smartpool.data.CarpoolMapper;
import smartpool.domain.Carpool;

public class CarpoolDao {
    private final SqlSessionFactory sqlSessionFactory;

    public CarpoolDao(SqlSessionFactory sqlSessionFactory) {

        this.sqlSessionFactory = sqlSessionFactory;
    }

    public CarpoolDao() {
        this(MyBatisConnectionFactory.getSqlSessionFactory());
    }

    public void insert(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CarpoolMapper carpoolMapper = sqlSession.getMapper(CarpoolMapper.class);
        carpoolMapper.insert(new Carpool(name));
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
}
